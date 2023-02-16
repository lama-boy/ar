package gui.input.button;

import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonPanel extends AbstractButtonPanel{
	@Override
	public void addButton(String name) {
		settingButton(new JButton(name), null);
	}

	@Override
	public void addButton(String name, ImageIcon icon) {
		addButton(name, icon, null);		
	}

	@Override
	public void addButton(String name, Consumer<?> c) {
		settingButton(new JButton(name), c);
	}

	@Override
	public void addButton(String name, ImageIcon icon, Consumer<?> c) {
		JButton button = new JButton(icon);
		button.setName(name);
		settingButton(button, c);
	}
}
