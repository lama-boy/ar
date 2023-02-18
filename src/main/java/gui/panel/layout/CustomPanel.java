package gui.panel.layout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CustomPanel {
	JPanel rootPanel = new JPanel();
	
	public void add(JComponent comp) {
		rootPanel.add(comp);
	}
	
	public void add(CustomPanel custom) {
		add(custom.rootPanel);
	}
	
	public JPanel panel() {
		return rootPanel;
	}
}
