package app;

import static app.ArApplication.IMG_PATH;
import static app.ArApplication.config;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.Integer.parseInt;
import static test.Debug.sysout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import gui.Gui;
import gui.input.button.ButtonPanel;

public class AppContainer {
	private CardLayout cardLayout = new CardLayout();
	private JPanel rootPane = new JPanel(new BorderLayout());
	private JPanel card = new JPanel(cardLayout);
	private JPanel container = new JPanel(new GridLayout(3,3,20,20));

	private JLabel timeLabel = new JLabel();
	private List<SubApp> runAppList = new Vector<>();
	private int cardIndex;
	
	//+++++++++++++++++++++++++++++++++++Style+++++++++++++++++++++++++++++++
	private int style = 0;
	private Color contBg, contBorder, topBotColor, lineColor;
	private Border iconLineBorder, iconEmptyBorder = BorderFactory.createEmptyBorder(2,2,2,2);
	private Font timeLabelFont = new Font(Font.SANS_SERIF,Font.PLAIN,17);
	
	public void setStyle(int style) {
		this.style = style;
		contBg = Color.decode(config.getProperty("contBg"+style, "#FAEECB")); 
	    contBorder = Color.decode(config.getProperty("contBorder"+style, "#7b630f"));
		topBotColor = Color.decode(config.getProperty("topBotColor"+style, "#001130"));
	    lineColor = Color.decode(config.getProperty("lineColor"+style, "#FF0000"));
	    iconLineBorder = BorderFactory.createLineBorder(lineColor, 2);
	}
	//+++++++++++++++++++++++++++++++++++Style+++++++++++++++++++++++++++++++
	//size
	private Dimension bottomBothSide = new Dimension(200,40);
	private int width = parseInt(config.getProperty("width", "700"));
	private int height = parseInt(config.getProperty("height", "700"));
	//------------------------------------------------------------------------
	
    public void initComponent() {
    	if(style == 0 ) setStyle(1);
    	runAppList.clear();
    	container.removeAll();
    	rootPane.removeAll();
    	
		card.setPreferredSize(new Dimension(width, height-80));
		container.setPreferredSize(card.getPreferredSize());
		container.setBorder(BorderFactory.createLineBorder(contBorder, 20));
		container.setBackground(contBg);

		ButtonPanel topBtnPanel = new ButtonPanel();
		topBtnPanel.setSize(width, 40);
		topBtnPanel.setBackground(topBotColor);
		for(int i=1; i<=8; i++) {
			final int a = i;
			topBtnPanel.addButton("button"+i, new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(a));
		}

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(topBotColor);
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setBackground(topBotColor);
		leftBottomPanel.setPreferredSize(bottomBothSide);
		
		ButtonPanel botBtnPanel = new ButtonPanel();
		botBtnPanel.setBackground(topBotColor);
		botBtnPanel.addButton("", new ImageIcon(IMG_PATH+"leftarrow.png"), b->move(-1));
		botBtnPanel.addButton("", new ImageIcon(IMG_PATH+"home.png"), b->move(0));
		botBtnPanel.addButton("", new ImageIcon(IMG_PATH+"rightarrow.png"), b->move(1));
		botBtnPanel.addButton("", new ImageIcon(IMG_PATH+"close.png"), b->move(2));
		
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setFont(timeLabelFont);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setPreferredSize(bottomBothSide);

		bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
		bottomPanel.add(botBtnPanel.getPanel(), CENTER);
		bottomPanel.add(timeLabel, BorderLayout.EAST);
		
		rootPane.add(topBtnPanel.getPanel(), BorderLayout.NORTH);
		rootPane.add(card, CENTER);
		rootPane.add(bottomPanel, SOUTH);
		
		container.setName("Container");
		card.add(container, container.getName());
	
		JFrame frame = new JFrame();
		frame.setContentPane(rootPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setTitle("비행기 예약 시스템");
		frame.setVisible(true);
		Gui.moveToCenter(frame);
		
		AppService.getInstance().addSubAppIcons();
	}
	
	public void removePanel(SubApp subApp) {
		if(subApp.close() && runAppList.contains(subApp)) {
			card.remove(subApp.getPanel());
			cardLayout.removeLayoutComponent(subApp.getPanel());
			runAppList.remove(subApp);
		}
	}
	
	public void addAppPanel(SubApp subApp) {
		if(!runAppList.contains(subApp)) {
			runAppList.add(subApp);
			card.add(subApp.getPanel(), subApp.getTitle());
		}
		cardLayout.show(card, subApp.getTitle());
	}
	
	public void addAppIcon(SubApp subApp) {
		JPanel iconPanel = new JPanel(new BorderLayout());
		if(subApp != null) {
			JLabel titleLabel = new JLabel(subApp.getTitle());
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			titleLabel.setFont(Gui.createFont("맑은 고딕", 28));
			iconPanel.add(titleLabel, SOUTH);
			Image image = Gui.getResizedImage(IMG_PATH+subApp.getClass().getSimpleName()+"2.PNG", 100, 100);
			JLabel iconLabel = new JLabel(new ImageIcon(image));
			iconPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			iconPanel.setBorder(iconEmptyBorder);
			iconPanel.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent e) { addAppPanel(subApp); }
				public void mouseEntered(MouseEvent e) { iconPanel.setBorder(iconLineBorder); }
				public void mouseExited(MouseEvent e) { iconPanel.setBorder(iconEmptyBorder); }
			});
			iconPanel.add(iconLabel, CENTER);
		}
		iconPanel.setBackground(contBg);
		container.add(iconPanel);
	}
	
	public void move(int d) {
		if(AppService.getInstance().getMember() == null) return;
		if(runAppList.isEmpty())
			return;
		if(d == 0) {
			cardLayout.show(card, container.getName());
		} else if(d == 2 && cardIndex >= 0 && cardIndex < runAppList.size()) {
			removePanel(runAppList.get(cardIndex));
		} else {
			cardIndex += d;
			if(cardIndex < 0) cardIndex = runAppList.size() -1;
			else if(cardIndex >= runAppList.size()) cardIndex = 0;
			cardLayout.show(card, runAppList.get(cardIndex).getTitle());
		}
	}
	
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd EEE HH:mm:ss");
	    String formattedTime = time.format(formatter);
		timeLabel.setText(formattedTime);
		runAppList.forEach(a->a.update(time));
	}
	
	public void action(int i) {
		if(i==1) {
			setStyle(1);
			initComponent();
		}
		if(i==2) { 
			setStyle(2); 
			initComponent();
		}
		sysout(i);
	}
}