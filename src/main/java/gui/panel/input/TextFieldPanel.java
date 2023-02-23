package gui.panel.input;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.panel.CustomPanel;
import gui.panel.InputPanel;

public class TextFieldPanel extends InputPanel{
	private JLabel label;
	private JTextField textField = new JTextField();
	
	{ rootPanel.setLayout(new BorderLayout()) ;}

	public TextFieldPanel() {
		this(null);
	}
	
	public TextFieldPanel(String name) {
		this(null, name);
	}
	
	public TextFieldPanel(String name, String labelText) {
		this(name, labelText, BorderLayout.WEST);
	}
	
	public TextFieldPanel(String name, String labelText, String labelDirection) {
		if(name != null) setName(name);
		if(labelText != null) {
			label = new JLabel();
			rootPanel.add(label, labelDirection);
		}
		rootPanel.add(textField, BorderLayout.CENTER);
	}
	
	public void setLabelText(String text) {
		label.setText(text);
	}

	public JLabel getLabel() {
		return label;
	}
	
	public JTextField getTextField() {
		return textField;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setValue(Object value) {
		
	}
	
	public void reset() {
		
	}
}
