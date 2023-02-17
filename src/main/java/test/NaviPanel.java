package test;

import java.awt.Dimension;

import javax.swing.JPanel;

import gui.Gui;
import gui.input.button.ButtonPanel;
import gui.input.button.CheckBoxPanel;
import gui.input.button.RadioButtonPanel;

public class NaviPanel{
	public NaviPanel() {
		JPanel content = new JPanel();
		
		content.setPreferredSize(new Dimension(500,500));
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.addButton("1click", c->print(1));
		buttonPanel.addButton("2click", c->print(2));
		
		content.add(buttonPanel.getPanel());
		
		RadioButtonPanel rbPanel = new RadioButtonPanel();
		rbPanel.addButton("1click", c->print(1));
		rbPanel.setSelected(0);
		
		rbPanel.addButton("2click", c->print(2));
		rbPanel.setSelected(true, "2click");
		
		content.add(rbPanel.getPanel());
		
		CheckBoxPanel cbpanel = new CheckBoxPanel();
		for (int i = 0; i < 3; i++) {
		    final int index = i;
		    cbpanel.addButton(index + "click", c -> print(index));
		}
		
//		Panel.addActionButton("4click", c->print(4));
//		
		cbpanel.setSelected(1);
//		cbpanel.setSelected(2);
		cbpanel.setSelected(0);
		
		content.add(cbpanel.getPanel());
		
		Gui.createFrame(content);
		System.out.println(rbPanel.getValue());
		System.out.println(cbpanel.getValues());
	}
	
	void print(Object object) {
		System.out.println("click :   " + object);
	}
		
	public static void main(String[] args) {
		new NaviPanel();
	}
}
