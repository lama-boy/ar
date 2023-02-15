package client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ArSubApp {
	private JPanel content = new JPanel();
	
	public String getTitle() {
		return this.getClass().getSimpleName();
	}
	public abstract ImageIcon getIcon();
	
	public JPanel getPanel() {
		return content;
	}
}
