package app;

import static app.ArApplication.IMG_PATH;
import static test.Debug.sysout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.login.LoginApp;
import app.reserv.SelectSeat;
import entity.TicketDTO;
import gui.Gui;
import gui.WrapFrame;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.BorderLayoutPanel;
import gui.wiget.ZonedClock;
import util.Style;

public class AppContainer {
	private JFrame frame = new JFrame();
	private BorderLayoutPanel rootPanel = new BorderLayoutPanel();
	private ConfigDialog config = new ConfigDialog(this);
	
	private CardLayout cardLayout = new CardLayout();
	private int cardIndex;
	private JPanel cardPanel;
	private AppView currentView;
	
	private final int rows = 3, cols = 3;
	private JPanel container;
	private List<AppView> viewList = new Vector<>();

	private BorderLayoutPanel topPanel, botPanel;
	
	private JLabel viewIconLabel = new JLabel();
	private JLabel viewTitleLabel = new JLabel();
	private JLabel timeLabel = new JLabel();
	private JLabel viewInfoLabel = new JLabel();
	
	//+++++++++++++++++++++++++++++++++++Style+++++++++++++++++++++++++++++++
	public Style style;
	private String timeFormat = "YYYY-MM-dd EEE HH:mm:ss";
	private Dimension botBothSide = new Dimension(200,50);
	private ImageIcon contIcon = new ImageIcon(IMG_PATH+"conticon.png");
	private Font subAppTitleFont = Gui.createFont("맑은 고딕", 28);
	private List<JPanel> iconPanels;
	private List<JLabel> titleLables;
	
	public void setStyle() {
		timeLabel.setFont(Gui.createFont(17));
		timeLabel.setFont(Gui.createFont(17));
		viewTitleLabel.setFont(Gui.createFont(28));

		viewTitleLabel.setForeground(style.getColor("fontColor"));
		viewInfoLabel.setForeground(style.getColor("fontColor"));
		timeLabel.setForeground(style.getColor("fontColor"));

		topPanel.setBackgrounds(style.getColor("topBotColor"));
		botPanel.setBackgrounds(style.getColor("topBotColor"));
		container.setBorder(BorderFactory.createLineBorder(style.getColor("contBorder"), 20));
		container.setBackground(style.getColor("contBg"));
		iconPanels.forEach(p->p.setBackground(style.getColor("contBg")));
		titleLables.forEach(l->l.setForeground(style.getColor("subTitle")));
	}
	//-----------------------------------Style---------------------------------
	public AppContainer() {
		config.loadStyle();
	}
	
    public void initRootPanel() {
    	viewList.clear();
    	rootPanel.removeAll();
    	
    	cardPanel = new JPanel(cardLayout);
    	container = new JPanel(new GridLayout(rows,cols,20,20));
    	
    	cardPanel.setPreferredSize(new Dimension(style.width, style.height-80));
		container.setPreferredSize(cardPanel.getPreferredSize());

		topPanel = new BorderLayoutPanel();
		JPanel topLeftPan = topPanel.newPanel(300, 40, BorderLayout.WEST);
		topLeftPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		topLeftPan.add(viewIconLabel);
		topLeftPan.add(viewTitleLabel);
		
		ButtonPanel topBtnPanel = new ButtonPanel();
		topBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		for(int i=1; i<=8; i++) {
			final int a = i;
			topBtnPanel.addButton(new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(a));
		}
		
		topPanel.addCenter(topBtnPanel);
		topPanel.newPanel(BorderLayout.EAST).add(Gui.createIconLabel(IMG_PATH+"config.png", 33, 33, b->config.open()));;
		
		botPanel = new BorderLayoutPanel();
		botPanel.newPanel(botBothSide, BorderLayout.WEST, FlowLayout.RIGHT).add(viewInfoLabel);
		viewInfoLabel.setPreferredSize(new Dimension(botBothSide.width - 20, botBothSide.height));
		
		ButtonPanel botBtnPan = new ButtonPanel();
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"leftarrow.png"), b->move(-1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"home.png"), b->move(-100));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"rightarrow.png"), b->move(1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"close.png"), b->move(-200));
		
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setPreferredSize(botBothSide);
		
		botPanel.addCenter(botBtnPan);
		botPanel.addEast(timeLabel);
		
		rootPanel.addNorth(topPanel);
		rootPanel.addCenter(cardPanel);
		rootPanel.addSouth(botPanel);
		
		container.setName("Container");
		cardPanel.add(container, container.getName());
		
		AppService.getInstance().updateSubAppIcons();

		move(-100);
		updateViewCount();
		setStyle();
	}
    
    /**
     * d == -100 : "Home",<br>
     * d == -200 : removeView()
     */
	public void move(int d) {
		if(AppService.getInstance().getMember() == null) return;
		
		currentView = null;
		if(d == -100 || viewList.isEmpty()) {
			cardLayout.show(cardPanel, container.getName());
			viewTitleLabel.setText("Home");
			viewIconLabel.setIcon(contIcon);
			cardIndex = -1;
		} else if(d == -200 && cardIndex >= 0 && cardIndex < viewList.size()) {
			removeView(viewList.get(cardIndex));
		} else {
			cardIndex += d;
			if(cardIndex < 0) cardIndex = viewList.size() -1;
			else if(cardIndex >= viewList.size()) cardIndex = 0;
			attachView(viewList.get(cardIndex));
		}
		updateViewCount();
	}

    public void removeViews(SubApp subApp) {
    	int prevSize = viewList.size();
    	viewList.removeIf(view -> subApp != null && view.parentApp() != null && view.parentApp().equals(subApp));
    	int removeCount = prevSize - viewList.size();
    	sysout("prevSize::"+ prevSize, "removeCount : "+ removeCount);
    	if(cardIndex != -1) move(-removeCount);
    }
    
	public void removeView(AppView appView) {
		if(appView != null && appView.close() && viewList.contains(appView)) {
			cardPanel.remove(appView.getPanel());
			cardLayout.removeLayoutComponent(appView.getPanel());
			viewList.remove(appView);
			move(-1);
		}
		updateViewCount();
		sysout("Remove View : " + appView, "View List:" +viewList);
	}
	
	public void addView(AppView appView) {
		if(appView == null) return;
		if(viewList.size() >= 25) {
			WrapFrame.alert("Max View Count is 25", Gui.font(50), cardPanel);
			return;
		}
		if(!viewList.contains(appView)) {
			viewList.add(appView);
			cardPanel.add(appView.getPanel(), appView.getClass().getName());
			cardIndex = viewList.size() -1;
		}else {
			cardIndex = viewList.indexOf(appView);
		}
		attachView(appView);
		sysout("Add View : " + appView, " View List:" + viewList);
	}
	
	private void attachView(AppView appView) {
		cardLayout.show(cardPanel, appView.getClass().getName());
		viewTitleLabel.setText(appView.getTitle());
		ImageIcon icon = appView.getImageIcon();
		if(icon == null) icon = contIcon;
		viewIconLabel.setIcon(icon);
		currentView = appView;
		updateViewCount();
	}
	
	public void addAppIcons(List<SubApp> appList) {
		iconPanels = new Vector<JPanel>();
		titleLables = new Vector<JLabel>();
		container.removeAll();
		for(int i=0; i<rows * cols; i++) {
			if(i < appList.size())
				addAppIcon(appList.get(i));
			else
				addAppIcon(null);
		}
		setStyle();
	}
	
	public void addAppIcon(SubApp subApp) {
		JPanel iconPanel = new JPanel(new BorderLayout());
		iconPanels.add(iconPanel);
		container.add(iconPanel);
		if(subApp == null) return;
		
		JLabel titleLabel = new JLabel(subApp.getTitle());
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(subAppTitleFont);
		titleLables.add(titleLabel);
		
		JLabel iconLabel = new JLabel(Gui.getResizedIcon(IMG_PATH+subApp.getClass().getSimpleName()+".PNG", IMG_PATH+"defaultimg.PNG", 100, 100));
		iconPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconPanel.add(iconLabel, BorderLayout.CENTER);
		iconPanel.add(titleLabel, BorderLayout.SOUTH);
		Gui.addBorderOnEnterMouse(iconPanel, b->addView(subApp.requestView()), 2);
	}
	
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
	    String formattedTime = time.format(formatter);
		timeLabel.setText(formattedTime);
		viewList.forEach(view->view.update(time));
	}
	
    public void showFrame() {
    	frame.dispose();
		frame = new JFrame("항공권 예약 어플리케이션");
		frame.setIconImage(contIcon.getImage());
		frame.setContentPane(rootPanel.getPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(style.width, style.height));
		Gui.moveToCenter(frame, style.width, style.height+100);
    }
	
	public JFrame getFrame() {
		return frame;
	}
	
	//_______________________________________DEBUG_______________________________________________
	public void updateViewCount() {
		String text = "<html>View Count : "+ viewList.size() + "<br>" +
					  "Card Index : "+ cardIndex + "<br> Current View : "+ 
				      (currentView != null ? currentView.getClass().getSimpleName() : "Home") +" </html>";
		viewInfoLabel.setText(text);
	}
	
	public void action(int i) {
		sysout("Debug Button :", i);
		AppService a = AppService.getInstance();

		if(i==1) {
			a.addSubApp(new LoginApp());
			a.updateSubAppIcons();
		}
		if(i==2) { 
			a.removeSubApp(a.getSubApp(LoginApp.class));
			a.updateSubAppIcons();
		}
		
		if(i==3) {
//			Gui.getFile(frame, new File(ArApplication.RES_PATH), ".jar");
//			File file = Gui.getFile(frame, new File(ArApplication.RES_PATH), ".jar");
//			sysout("selected File : ", file);
			
			
			SelectSeat s = new SelectSeat(null, new TicketDTO());
			
			addView(s);
		}
//			AppService.getInstance().updateSubAppIcons();
		
		if(i==4) {
			addView(
				new AppView() {
					ZonedClock z = new ZonedClock(200);
					{initRootPanel();}
					@Override
					public void initRootPanel() {
						z.setFontColor(Gui.createFont(20), Color.yellow, Color.BLACK);
						z.initRootPanel();

						rootPanel.add(z.getPanel());
						
						rootPanel.add(new JLabel("asds"));
					}
					@Override
					public void update(LocalDateTime time) {
						z.setTime(time);
					}
				}
			);
		}
		
		if(i == 5) {
			initRootPanel();
			sysout("initRootPanel()");
		}
		if( i == 6) {
			WrapFrame.greenAlert("Gr한글은어떨가요?n23ert", cardPanel);
		}
		
		if(i == 7) {
			WrapFrame.alert("Gr한글은어떨가요?nAL숫자함?23ert!!", cardPanel);
		}
	}
	//_______________________________________DEBUG_______________________________________________
}