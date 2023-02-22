package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.ArApplication;
import gui.Gui;
import gui.WrapFrame;

class A{
	public static void main(String[] args) {
		JPanel parent = new JPanel();
		parent.setPreferredSize(new Dimension(400, 400));
		String msg = "asdasdsad";
		Font font = null;
		
		Gui.moveToCenter(Gui.createFrame(parent));
		
		WrapFrame frame = new WrapFrame(parent);
		frame.getFrame().getContentPane().setLayout(null);
		JPanel inPanel = new JPanel();
		inPanel.setBackground(new Color(231, 255, 220));
		Dimension size = parent.getSize();
		int inWidth = 500, inHeight = 60;
		inPanel.setBounds(size.width/2-inWidth/2, size.height/2 - inHeight/2 , inWidth, inHeight);
		JLabel iconLabel = Gui.createIconLabel(ArApplication.IMG_PATH+"success.png");
		if(size.width < 50 || size.height < 50)
			iconLabel.setIcon(Gui.getResizedIcon(ArApplication.IMG_PATH+"success.png", Math.min(size.width, size.height), Math.min(size.width, size.height)));
		inPanel.add(iconLabel);
//		inPanel.setPreferredSize(new Dimension(size.width/5, size.height/2));
		if(msg != null) {
			JLabel label = new JLabel(msg);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(new Color(0, 123, 0));
			if(font == null) font = Gui.font(40);
			label.setFont(font);
			inPanel.add(label);
		}
		frame.getFrame().getContentPane().add(inPanel, BorderLayout.CENTER);
		frame.setPanelColor(new Color(240, 255, 250));
		frame.setProperties(0, 0.12f, 0.8f, 230);
		frame.start();
	}
}