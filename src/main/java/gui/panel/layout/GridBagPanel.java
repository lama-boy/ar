package gui.panel.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;

import gui.panel.CustomPanel;

public class GridBagPanel extends CustomPanel{
	private GridBagConstraints gbc = new GridBagConstraints();
	
	{ rootPanel.setLayout(new GridBagLayout()); }

	public void add(JComponent comp, int gridx, int gridy, int weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		rootPanel.add(comp, gbc);
	}
	
	public void add(JComponent comp, int gridx, int gridy) {
		add(comp, gridx, gridy, 1, 1);
	}
	
	public GridBagConstraints getConstraints() {
		return gbc;
	}
}
