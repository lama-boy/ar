package gui.panel.button;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import gui.panel.input.InputComponent;

public class CheckBoxPanel extends AbstractButtonPanel implements InputComponent{

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

	public List<AbstractButton> getSelectedButtons() {
		return getButtonList().stream().filter(AbstractButton::isSelected)
				.collect(Collectors.toList());
	}

	@Override
	public String getValue() {
		return getButtonList().stream().filter(AbstractButton::isSelected)
				.map(b->b.getName()).collect(Collectors.joining(","));
	}
	
	@Override
	public void setValue(Object value) {
		String val = value.toString();
		buttonList.forEach(b->{
			if(b.getName().equals(val)) {
				b.setSelected(true);
				return;
			}
		});
		if (val.matches("-?\\d+(\\.\\d+)?")) {
			int i = Integer.parseInt(val);
			if(i>=0 && i<buttonList.size())
				buttonList.get(i).setSelected(true);
        }
	}

	@Override
	public void reset() {
		buttonList.forEach(b->b.setSelected(false));
	}
}
