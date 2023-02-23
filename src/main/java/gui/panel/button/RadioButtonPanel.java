package gui.panel.button;

import java.util.function.Consumer;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import gui.panel.input.InputComponent;

public class RadioButtonPanel extends AbstractButtonPanel implements InputComponent{
	ButtonGroup buttonGroup = new ButtonGroup();
	
	@Override
	public void addButton(String name, Consumer<?> c) {
		settingButton(new JRadioButton(name), c);
	}

	@Override
	public void addButton(String name, ImageIcon icon, Consumer<?> c) {
		settingButton(new JRadioButton(name, icon), c);
	}

	@Override
	public void addButton(String name) {
		settingButton(new JRadioButton(name), null);
	}

	@Override
	public void addButton(String name, ImageIcon icon) {
		settingButton(new JRadioButton(name, icon), null);
	}

	@Override
	public void addButton(ImageIcon icon, Consumer<?> c) {
		settingButton(new JRadioButton(icon), null);
	}
	
	@Override
	public void settingButton(AbstractButton button, Consumer<?> c) {
		buttonGroup.add(button);
		super.settingButton(button, c);
	}
	
	@Override
	public String getValue() {
		for(AbstractButton button : getButtonList())
			if(button.isSelected()) return button.getName();
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		if(!buttonList.isEmpty()) buttonList.get(0).setSelected(true);
	}
}
