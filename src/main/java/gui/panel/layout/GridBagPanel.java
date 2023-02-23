package gui.panel.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.panel.CustomPanel;

public class GridBagPanel extends CustomPanel{
	private GridBagConstraints gbc = new GridBagConstraints();
	private int maxgridx;
	
	{ rootPanel.setLayout(new GridBagLayout()); }

	public void add(CustomPanel panel, int gridx, int gridy, int weightx, int weighty) {
		add(panel.getPanel(), gridx, gridy, weightx, weighty);
	}
	
	public void add(JComponent comp, int gridx, int gridy, int weightx, int weighty) {
		gbc.gridx = gridx;
		if(gridx > maxgridx) maxgridx = gridx;
		
		gbc.gridy = gridy;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		rootPanel.add(comp, gbc);
	}
	
	public void add(CustomPanel panel, int gridx, int gridy) {
		add(panel, gridx, gridy, 1, 1);
	}
	
	public void add(JComponent comp, int gridx, int gridy) {
		add(comp, gridx, gridy, 1, 1);
	}
	
	public GridBagConstraints getConstraints() {
		return gbc;
	}
	
	public JPanel newPanel(int row, int weighty) {
		JPanel panel = new JPanel();
		add(panel, 1, row, 1, weighty);
		return panel;
	}
	
	public JPanel newPanel() {
		return newPanel(++gbc.gridx, 1);
	}
	
	public void addNextRow(CustomPanel panel) {
		gbc.gridwidth = maxgridx;
		add(panel.getPanel(), 1, ++gbc.gridy, 1, 1);
	}
	
	public void removeAll() {
		super.removeAll();
		gbc = new GridBagConstraints();
	}
}
