package client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import gui.Gui;

public class Reservation extends ArSubApp {
	@Override
	public String getTitle() {
		return "Reservation";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon("src/main/resources/image/heart.png");
	}
}
