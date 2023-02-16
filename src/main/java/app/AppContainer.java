package app;

import static app.ArApplication.IMG_PATH;
import static test.Debug.sysout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JPanel container = new JPanel();

	private JLabel timeLabel = new JLabel("00:00:00");
	
	private SubApp runApp;
	
	public void removePanel() {
		if(runApp != null) removePanel(runApp);
	}
	
	public void removePanel(SubApp subApp) {
		if(runApp.close()) {
			card.remove(runApp.getPanel());
			cardLayout.removeLayoutComponent(runApp.getPanel());
			runApp = null;
		}
	}
	
	public void addPanel(SubApp subApp) {
		runApp = subApp;
		card.add(subApp.getPanel(), subApp.getTitle());
		cardLayout.show(card, subApp.getTitle());
	}
	
	public void display(int width, int height) {
		card.setPreferredSize(new Dimension(width, height - 60));
		
		ButtonPanel naviPanel = new ButtonPanel();
		naviPanel.setBackground(new Color(0, 11, 50));
		for(int i=1; i<=8; i++) {
			final int i2 = i;
			naviPanel.addButton("button"+i, new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->action(i2));
		}
		naviPanel.getPanel().add(timeLabel);
		timeLabel.setForeground(Color.WHITE);
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setBackground(new Color(0, 23, 49));
		buttonPanel.addButton("<<", b->move(-1));
		buttonPanel.addButton("□",  b->move(0));
		buttonPanel.addButton(">>", b->move(1));
		buttonPanel.addButton("X", b->move(2));
		
		rootPane.add(naviPanel.getPanel(), BorderLayout.NORTH);
		rootPane.add(card, BorderLayout.CENTER);
		rootPane.add(buttonPanel.getPanel(), BorderLayout.SOUTH);
		
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
	
	public void addSubAppIcon(SubApp subApp) {
		container.add(new JLabel(subApp.getTitle()));
		Image image = Gui.getResizedImage(IMG_PATH+subApp.getIconName(), 100, 100);
		JLabel iconLabel = new JLabel(new ImageIcon(image));
		iconLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPanel(subApp);
			}
		});
		container.add(iconLabel);
	}
	
	public void move(int i) {
		if(AppService.getInstance().getMember() == null) return;
		
		switch (i) {
			case -1: cardLayout.next(card); break;
			case  0: cardLayout.show(card, container.getName()); break;
			case  1: cardLayout.previous(card); break;
			case  2: removePanel(); move(0); break;
		}
	}
	
	public void update(String text) {
		timeLabel.setText(text);
	}
	
	public void action(int i) {
		sysout(i);
	}
}
