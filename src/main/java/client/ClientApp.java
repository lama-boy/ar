package client;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Gui;

public class ClientApp{
	JPanel content = new JPanel();
	JFrame frame;
	
	public ClientApp() {
		Reservation r = new Reservation();
		addApp(r);
		
		DashBoard b = new DashBoard();
		addApp(b);
		
		content.setPreferredSize(new Dimension(500, 500));
		frame = Gui.createFrame(content);
		frame.setTitle("비행기 예약 시스템");
	}

	public static void main(String[] args) {
		new ClientApp();
	}
	
	public void addApp(ArSubApp app) {
		content.add(new JLabel(app.getTitle()));
		Image newImage = app.getIcon().getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		content.add(new JLabel(new ImageIcon(newImage)));
	}
}
