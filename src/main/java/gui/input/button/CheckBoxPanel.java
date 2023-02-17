package gui.input.button;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CheckBoxPanel extends AbstractButtonPanel{

	@Override
	public void addButton(String name, Consumer<?> c) {
		settingButton(new JCheckBox(name), c);
	}

	@Override
	public void addButton(String name, ImageIcon icon, Consumer<?> c) {
		settingButton(new JCheckBox(name, icon), c);
	}

	@Override
	public void addButton(String name) {
		settingButton(new JCheckBox(name), null);
	}

	@Override
	public void addButton(String name, ImageIcon icon) {
		settingButton(new JCheckBox(name, icon), null);
	}
	
	@Override
	public void addButton(ImageIcon icon, Consumer<?> c) {
		settingButton(new JCheckBox(icon), null);
	}

	@Override
	public String getValue() {
		return getButtonList().stream().filter(AbstractButton::isSelected)
				.map(b->b.getName()).collect(Collectors.joining(","));
	}

	public List<AbstractButton> getSelectedButtons() {
		return getButtonList().stream().filter(AbstractButton::isSelected)
				.collect(Collectors.toList());
	}
}
