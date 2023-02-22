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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Gui;
import gui.WrapFrame;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.BorderLayoutPanel;
import gui.wiget.ZonedClock;
import util.SettingDialog;

public class AppContainer {
	private BorderLayoutPanel rootPanel = new BorderLayoutPanel();
	private JFrame frame = new JFrame();
	private SettingDialog settingDialog = new SettingDialog(this);
	
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
	
	private JLabel timeLabel = new JLabel(), viewInfoLabel = new JLabel();
	//+++++++++++++++++++++++++++++++++++Style+++++++++++++++++++++++++++++++
	private int width, height;
	private Color contBg, contBorder, topBotColor;
	private String timeForamt = "YYYY-MM-dd EEE HH:mm:ss";
	private Dimension botBothSide = new Dimension(200,50);
	private ImageIcon contIcon = new ImageIcon(IMG_PATH+"conticon.png");
	private Font subAppTitleFont = Gui.createFont("맑은 고딕", 28);
	private List<JPanel> iconPanels;
	
	public void setStyle(Properties style) {
		String s = style.getProperty("style","1");
		viewTitleLabel.setForeground(Color.WHITE);
		viewTitleLabel.setFont(Gui.createFont(28));
		timeLabel.setFont(Gui.createFont(17));
		timeLabel.setForeground(Color.WHITE);
		contBg = Color.decode(style.getProperty("contBg"+s, "#FAEECB")); 
	    contBorder = Color.decode(style.getProperty("contBorder"+s, "#7b630f"));
		topBotColor = Color.decode(style.getProperty("topBotColor"+s, "#001130"));
	    width = Integer.parseInt(style.getProperty("width", "700"));
	    height = Integer.parseInt(style.getProperty("height", "700"));
	    viewInfoLabel.setForeground(Color.white);
	    
	    container.setBackground(contBg);
	    container.setBorder(BorderFactory.createLineBorder(contBorder, 20));
		topPanel.setBackgrounds(topBotColor);
		botPanel.setBackgrounds(topBotColor);
		iconPanels.forEach(p->p.setBackground(contBg));
	}
	//-----------------------------------Style---------------------------------
	
    public void initComponent() {
    	viewList.clear();
    	rootPanel.getPanel().removeAll();
    	
    	cardPanel = new JPanel(cardLayout);
    	container = new JPanel(new GridLayout(rows,cols,20,20));
    	
    	cardPanel.setPreferredSize(new Dimension(width, height-80));
		container.setPreferredSize(cardPanel.getPreferredSize());

		topPanel = new BorderLayoutPanel();
		JPanel topLeftPan = topPanel.newPanel(300, 40, BorderLayout.WEST);
		topLeftPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		topLeftPan.add(viewIconLabel);
		topLeftPan.add(viewTitleLabel);
		
		ButtonPanel topBtnPanel = new ButtonPanel();
		topBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		for(int i=1; i<=4; i++) {
			final int a = i;
			topBtnPanel.addButton(new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(a));
		}
		
		topPanel.addCenter(topBtnPanel);
		topPanel.newPanel(BorderLayout.EAST).add(Gui.createIconLabel(IMG_PATH+"config.png", 33, 33, b->{
			settingDialog.open();
			Gui.placeSubWindow(getFrame(), settingDialog.getDialog(), 1);	
			settingDialog.getDialog().setModal(true);
		}));;
		
		botPanel = new BorderLayoutPanel();
		botPanel.newPanel(botBothSide, BorderLayout.WEST, FlowLayout.RIGHT).add(viewInfoLabel);
		viewInfoLabel.setPreferredSize(new Dimension(botBothSide.width - 20, botBothSide.height));
		
		ButtonPanel botBtnPan = new ButtonPanel();
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"leftarrow.png"), b->move(-1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"home.png"), b->move(0));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"rightarrow.png"), b->move(1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"close.png"), b->move(-10));
		
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

		move(0);
		updateViewCount();
//		rootPanel.getPanel().revalidate();
	}

    public void showFrame() {
    	frame.dispose();
		frame = new JFrame("항공권 예약 시스템");
		frame.setIconImage(contIcon.getImage());
		frame.setContentPane(rootPanel.getPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(width, height));
		Gui.moveToCenter(frame, width, height+80);
    }
    
    public void removeViews(SubApp subApp) {
    	viewList.removeIf(view -> subApp != null && view.parentApp() != null && view.parentApp().equals(subApp));
    	move(0);
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
		sysout("Add View : " + appView, " View List:" +viewList);
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
		for(int i=0; i<rows*cols; i++) {
			if(i < appList.size())
				addAppIcon(appList.get(i));
			else
				addAppIcon(null);
		}
	}
	
	public void addAppIcon(SubApp subApp) {
		JPanel iconPanel = new JPanel(new BorderLayout());
		iconPanels.add(iconPanel);
		container.add(iconPanel);
		if(subApp == null) return;
		
		JLabel titleLabel = new JLabel(subApp.getTitle());
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(subAppTitleFont);
		
		JLabel iconLabel = new JLabel(Gui.getResizedIcon(IMG_PATH+subApp.getClass().getSimpleName()+".PNG", IMG_PATH+"defaultimg.PNG", 100, 100));
		iconPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconPanel.add(iconLabel, BorderLayout.CENTER);
		iconPanel.add(titleLabel, BorderLayout.SOUTH);
		Gui.addBorderOnEnterMouse(iconPanel, b->addView(subApp.requestView()), 2);
	}
	
	public void move(int d) {
		if(AppService.getInstance().getMember() == null) return;
		
		currentView = null;
		if(d == 0 || viewList.isEmpty()) {
			cardLayout.show(cardPanel, container.getName());
			viewTitleLabel.setText("Home");
			viewIconLabel.setIcon(contIcon);
			cardIndex = -1;
		} else if(d == -10 && cardIndex >= 0 && cardIndex < viewList.size()) {
			removeView(viewList.get(cardIndex));
		} else {
			cardIndex += d;
			if(cardIndex < 0) cardIndex = viewList.size() -1;
			else if(cardIndex >= viewList.size()) cardIndex = 0;
			attachView(viewList.get(cardIndex));
		}
		updateViewCount();
	}
	
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeForamt);
	    String formattedTime = time.format(formatter);
		timeLabel.setText(formattedTime);
		viewList.forEach(view->view.update(time));
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	//_______________________________________DEBUG_______________________________________________
	public void updateViewCount() {
		String text = "<html>View Count : "+ viewList.size() + "<br>" +
						    "Card Index : "+ cardIndex + "<br>" +
		                    "Current View : "+ (currentView != null ? currentView.getClass().getSimpleName() : "Home") +" </html>";
		viewInfoLabel.setText(text);
	}
	
	public void action(int i) {
//		AppService a = AppService.getInstance();

		if(i==1) {
//			a.addSubApp(new LoginApp());
//			a.updateSubAppIcons();
		}
		if(i==2) { 
//			initComponent();
//			a.removeSubApp(a.getSubApp(LoginApp.class));
		}
		
		if(i==3) {
			Gui.getFile(frame, new File(ArApplication.RES_PATH), ".jar");
//			File file = Gui.getFile(frame, new File(ArApplication.RES_PATH), ".jar");
//			sysout("selected File : ", file);
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
				});
			}
		sysout(i);
		
		if(i == 5) {
			WrapFrame.mouseTooltip(viewInfoLabel, "TOOL TIP!!", 150, 45, null);
		}
		if( i == 6) {
			WrapFrame.greenAlert(container);
		}
		
		if(i == 7) {
			WrapFrame.alert("alert!!", container);
		}
	}
	//_______________________________________DEBUG_______________________________________________
}