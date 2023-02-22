package gui.panel.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.panel.CustomPanel;

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
	
	public JPanel newPanel(int width, int height, String direction) {
		return newPanel(new Dimension(width, height), direction, 0);
	}
	
	public JPanel newPanel(Dimension dim, String direction, int alignment) {
		JPanel panel = new JPanel(new FlowLayout(alignment));
		if(dim != null) panel.setPreferredSize(dim);
		rootPanel.add(panel, direction);
		return panel;
	}
	
	public JScrollPane newScroll(JComponent comp, String direction) {
		JScrollPane scrollPane = new JScrollPane(comp);
		rootPanel.add(scrollPane, direction);
		return scrollPane;
	}
	
	public JPanel newPanel(String direction) {
		return newPanel(null, direction, 0);
	}
}
