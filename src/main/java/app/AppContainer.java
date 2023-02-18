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
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
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
import javax.swing.border.Border;

import gui.Gui;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.BorderLayoutPanel;
import test.Debug;

public class AppContainer {
	private BorderLayoutPanel rootPanel = new BorderLayoutPanel();
	private JFrame frame = new JFrame();
	
	private CardLayout cardLayout = new CardLayout();
	private int cardIndex;
	private JPanel cardPanel = new JPanel(cardLayout);
	
	private final int rows = 3, cols = 3;
	private JPanel container = new JPanel(new GridLayout(rows,cols,20,20));
	private List<AppView> viewList = new Vector<>();

	private JLabel viewIconLabel = new JLabel();
	private JLabel viewTitleLabel = new JLabel();
	
	private JLabel timeLabel = new JLabel(), viewCount = new JLabel("View Count : 0");
	//+++++++++++++++++++++++++++++++++++Style+++++++++++++++++++++++++++++++
	private Properties style = new Properties();
	private int width, height;
	private Color contBg, contBorder, topBotColor, lineColor;
	private Border iconLineBorder, iconEmptyBorder = BorderFactory.createEmptyBorder(2,2,2,2);
	private String timeForamt = "YYYY-MM-dd EEE HH:mm:ss";
	private Dimension botBothSide = new Dimension(200,40);
	private ImageIcon contIcon = new ImageIcon(IMG_PATH+"conticon.png"); 
	
	public void setStyle() {
		try { style.load(new FileReader(ArApplication.RES_PATH+"style.properties")); } 
		catch (IOException e) {	e.printStackTrace(); }
		String s = style.getProperty("style", "1");
		viewTitleLabel.setForeground(Color.WHITE);
		viewTitleLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,28));
		timeLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,17));
		timeLabel.setForeground(Color.WHITE);
		contBg = Color.decode(style.getProperty("contBg"+s, "#FAEECB")); 
	    contBorder = Color.decode(style.getProperty("contBorder"+s, "#7b630f"));
		topBotColor = Color.decode(style.getProperty("topBotColor"+s, "#001130"));
	    lineColor = Color.decode(style.getProperty("lineColor"+s, "#FF0000"));
	    iconLineBorder = BorderFactory.createLineBorder(lineColor, 2);
	    width = Integer.parseInt(style.getProperty("width", "700"));
	    height = Integer.parseInt(style.getProperty("height", "700"));
	    
	    viewCount.setForeground(Color.white);
	}
	//-----------------------------------Style---------------------------------
	
    public void initComponent() {
    	setStyle();
    	frame.dispose();
    	viewList.clear();
    	rootPanel.panel().removeAll();
    	cardPanel.removeAll();
    	
    	cardPanel.setPreferredSize(new Dimension(width, height-80));
		container.setPreferredSize(cardPanel.getPreferredSize());
		container.setBorder(BorderFactory.createLineBorder(contBorder, 20));
		container.setBackground(contBg);

		BorderLayoutPanel topPanel = new BorderLayoutPanel();
		JPanel topLeftPan = topPanel.newPanel(300, 40, BorderLayout.WEST);
		topLeftPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		topLeftPan.add(viewIconLabel);
		topLeftPan.add(viewTitleLabel);
		
		ButtonPanel topBtnPanel = new ButtonPanel();
		topBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topBtnPanel.setSize(width, 40);
		for(int i=1; i<=2; i++) {
			final int a = i;
			topBtnPanel.addButton(new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(a));
		}
		
		topPanel.addCenter(topBtnPanel);
		topPanel.newPanel(botBothSide, BorderLayout.EAST);
		topPanel.setBackgrounds(topBotColor);
		
		BorderLayoutPanel botPanel = new BorderLayoutPanel();
		botPanel.newPanel(botBothSide, BorderLayout.WEST).add(viewCount);
		
		ButtonPanel botBtnPan = new ButtonPanel();
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"leftarrow.png"), b->move(-1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"home.png"), b->move(0));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"rightarrow.png"), b->move(1));
		botBtnPan.addButton(new ImageIcon(IMG_PATH+"close.png"), b->move(-10));
		
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setPreferredSize(botBothSide);
		
		botPanel.addCenter(botBtnPan);
		botPanel.addEast(timeLabel);
		botPanel.setBackgrounds(topBotColor);
		
		rootPanel.addNorth(topPanel);
		rootPanel.addCenter(cardPanel);
		rootPanel.addSouth(botPanel);
		
		container.setName("Container");
		cardPanel.add(container, container.getName());
		
		AppService.getInstance().addSubAppIcons();

		frame = new JFrame("항공권 예약 시스템");
		frame.setContentPane(rootPanel.panel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		Gui.moveToCenter(frame);
		move(0);
	}
	
	public void removeView(AppView appView) {
		if(appView != null && appView.close() && viewList.contains(appView)) {
			cardPanel.remove(appView.getPanel());
			cardLayout.removeLayoutComponent(appView.getPanel());
			viewList.remove(appView);
		}
		viewCount.setText("View Count : " + viewList.size());
	}
	
	public void openView(AppView appView) {
		if(appView == null) return;
		if(!viewList.contains(appView)) {
			viewList.add(appView);
			cardPanel.add(appView.getPanel(), appView.getClass().getName());
			sysout(appView.getClass().getName());
		}
		attachView(appView);
	}
	
	private void attachView(AppView appView) {
		cardLayout.show(cardPanel, appView.getClass().getName());
		Debug.sysout(appView.getTitle());
		viewTitleLabel.setText(appView.getTitle());
		ImageIcon icon = appView.getImageIcon();
		if(icon == null) icon = contIcon;
		viewIconLabel.setIcon(icon);
		
		viewCount.setText("View Count : " + viewList.size());
	}
	
	public void addAppIcons(List<SubApp> appList) {
		container.removeAll();
		for(int i=0; i<rows*cols; i++) {
			if(i < appList.size())
				addAppIcon(appList.get(i));
			else
				addAppIcon(null);
		}
	}
	
	public void addAppIcon(SubApp subApp) {
		JPanel iconPanel = new JPanel(new BorderLayout());
		container.add(iconPanel);
		iconPanel.setBackground(contBg);
		if(subApp == null) return;
		
		JLabel titleLabel = new JLabel(subApp.getTitle());
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(Gui.createFont("맑은 고딕", 28));
		
		Image image = Gui.getResizedImage(IMG_PATH+subApp.getClass().getSimpleName()+".PNG", 100, 100);
		JLabel iconLabel = new JLabel(new ImageIcon(image));
		iconPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconPanel.setBorder(iconEmptyBorder);
		iconPanel.add(iconLabel, BorderLayout.CENTER);
		iconPanel.add(titleLabel, BorderLayout.SOUTH);
		iconPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { openView(subApp.requestView()); }
			public void mouseEntered(MouseEvent e) { iconPanel.setBorder(iconLineBorder); }
			public void mouseExited(MouseEvent e) { iconPanel.setBorder(iconEmptyBorder); }
		});
	}
	
	public void move(int d) {
		if(AppService.getInstance().getMember() == null) return;
		
		if(d == 0 || viewList.isEmpty()) {
			cardLayout.show(cardPanel, container.getName());
			viewTitleLabel.setText("Home");
			viewIconLabel.setIcon(contIcon);
			return;
		} 
		if(d == -10 && cardIndex >= 0 && cardIndex < viewList.size()) {
			removeView(viewList.get(cardIndex));
			move(-1);
		} else {
			cardIndex += d;
			if(cardIndex < 0) cardIndex = viewList.size() -1;
			else if(cardIndex >= viewList.size()) cardIndex = 0;
			openView(viewList.get(cardIndex));
		}
	}
	
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeForamt );
	    String formattedTime = time.format(formatter);
		timeLabel.setText(formattedTime);
		viewList.forEach(a->a.update(time));
	}
	
	public void action(int i) {
		if(i==1) {
			style.setProperty("style", "1");
			initComponent();
		}
		if(i==2) { 
			style.setProperty("style", "2");
			initComponent();
		}
		
		if(i==3)
			AppService.getInstance().addSubAppIcons();
		sysout(i);
	}
}