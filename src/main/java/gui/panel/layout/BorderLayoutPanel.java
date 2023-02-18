package gui.panel.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BorderLayoutPanel extends CustomPanel{
	{ rootPanel.setLayout(new BorderLayout()); }
	
	public void addNorth(CustomPanel panel) {
		addNorth(panel.panel());
	}
	
	public void addWest(CustomPanel panel) {
		addWest(panel.panel());
	}
	
	public void addCenter(CustomPanel panel) {
		addCenter(panel.panel());
	}
	
	public void addEast(CustomPanel panel) {
		addEast(panel.panel());
	}
	
	public void addSouth(CustomPanel panel) {
		addSouth(panel.panel());
	}
	
	public void addNorth(JComponent comp) {
		rootPanel.add(comp, BorderLayout.NORTH);
	}
	
	public void addWest(JComponent comp) {
		rootPanel.add(comp, BorderLayout.WEST);
	}
	
	public void addCenter(JComponent comp) {
		rootPanel.add(comp, BorderLayout.CENTER);
	}
	
	public void addEast(JComponent comp) {
		rootPanel.add(comp, BorderLayout.EAST);
	}
	
	public void addSouth(JComponent comp) {
		rootPanel.add(comp, BorderLayout.SOUTH);
	}
	
	public void setBackgrounds(Color color) {
		rootPanel.setBackground(color);
		for(Component comp : rootPanel.getComponents()) {
			comp.setBackground(color);
		}
	}
	
	public JPanel newPanel(int width, int height, String dir) {
		return newPanel(new Dimension(width, height), dir);
	}
	
	public JPanel newPanel(Dimension dim, String dir) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(dim);
		rootPanel.add(panel, dir);
		return panel;
	}
}
