package gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class CustomPanel {
	protected JPanel rootPanel = new JPanel();
	
	public void add(JComponent comp) {
		rootPanel.add(comp);
	}
	
	public void add(CustomPanel custom) {
		add(custom.rootPanel);
	}
	
	public JPanel panel() {
		return rootPanel;
	}
	
	public void setSize(int width, int height) {
		rootPanel.setPreferredSize(new Dimension(width, height));
	}
	
	public void setLayout(LayoutManager layout) {
		rootPanel.setLayout(layout);
	}
	
	public JPanel getPanel() {
		return rootPanel;
	}
	
	public CustomPanel setBackground(Color bgColor) {
		rootPanel.setBackground(bgColor);
		return this;
	}
	
	public void removeAll() {
		rootPanel.removeAll();
	}
}
