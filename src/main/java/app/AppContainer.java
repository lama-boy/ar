package app;

import static app.ArApplication.IMG_PATH;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static test.Debug.sysout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private Color contBg = Color.decode("#FAEECB"), contBorder = Color.decode("#7b630f");
	
	public void initComponent(int width, int height) {
		card.setPreferredSize(new Dimension(width, height-80));
		container.setPreferredSize(card.getPreferredSize());
		container.setBorder(BorderFactory.createLineBorder(contBorder, 20));
		container.setBackground(contBg);

		Color bottomColor = new Color(0, 11, 50);
		ButtonPanel topBtnPanel = new ButtonPanel();
		topBtnPanel.setSize(width, 40);
		topBtnPanel.setBackground(bottomColor);
		for(int i=1; i<=8; i++) {
			final int a = i;
			topBtnPanel.addButton("button"+i, new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(a));
		}

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(bottomColor);
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setBackground(bottomColor);
		Dimension bottomBothSide = new Dimension(150,40);
		leftBottomPanel.setPreferredSize(bottomBothSide);
		
		ButtonPanel botBtnPanel = new ButtonPanel();
		botBtnPanel.setBackground(bottomColor);
		botBtnPanel.addButton("<<", b->move(-1));
		botBtnPanel.addButton("□",  b->move(0));
		botBtnPanel.addButton(">>", b->move(1));
		botBtnPanel.addButton("X", b->move(2));
		
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
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
			iconPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
			iconPanel.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent e) { addAppPanel(subApp); }
				public void mouseEntered(MouseEvent e) { iconPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); }
				public void mouseExited(MouseEvent e) { iconPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1)); }
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
		sysout(i);
	}
}
