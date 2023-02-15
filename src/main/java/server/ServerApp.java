package server;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.Gui;

public class ServerApp {
	public static void main(String[] args) {
		new ServerApp().run();
	}

	private void run() {
		JPanel content = new JPanel(new BorderLayout());
		Gui.createFrame(content).setTitle("비행기 예약 시스템");
	}
}
