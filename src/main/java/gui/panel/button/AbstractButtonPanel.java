package gui.panel.button;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import gui.panel.CustomPanel;

public abstract class AbstractButtonPanel extends CustomPanel{
	private List<AbstractButton> buttonList = new Vector<>();
	
	public abstract void addButton(String name, Consumer<?> c);
	
	public abstract void addButton(String name, ImageIcon icon, Consumer<?> c);
	
	public abstract void addButton(String name);
	
	public abstract void addButton(String name, ImageIcon icon);

	public abstract void addButton(ImageIcon icon, Consumer<?> c);

	public List<AbstractButton> getButtonList() {
		return buttonList;
	}

	protected void settingButton(AbstractButton button, Consumer<?> c) {
		buttonList.add(button);
		button.setName(button.getText());
		if (c != null) {
			button.addActionListener(e -> c.accept(null));
		}
		rootPanel.add(button);
	}

	public List<String> getValues() {
		return buttonList.stream().filter(AbstractButton::isSelected)
				.map(AbstractButton::getName).collect(Collectors.toList());
	}

	public AbstractButton getButton(int index) {
		return buttonList.get(index);
	}

	public AbstractButton getButton(String name) {
		for (AbstractButton button : buttonList) {
			if (button.getName() != null && button.getName().equals(name))
				return button;
		}
		return null;
	}

	public void setSelected(int index) {
		setSelected(true, index);
	}

	public void setSelected(boolean isSelected, String name) {
		buttonList.forEach(button -> {
			if (button.getName() != null && button.getName().equals(name)) {
				button.setSelected(isSelected);
				return;
			}
		});
	}

	public void setSelected(boolean isSelected, int... indexes) {
		for (int i = 0; i < indexes.length; i++) {
			int index = indexes[i];
			if (index >= 0 && index < buttonList.size())
				buttonList.get(index).setSelected(isSelected);
		}
	}

	public void setFont(int index, Font font) {
		buttonList.get(index).setFont(font);
	}

	public void setFonts(Font font) {
		buttonList.forEach(b -> b.setFont(font));
	}

	public void setColors(int index, Color foreground) {
		buttonList.get(index).setForeground(foreground);
	}

	public void setColors(Color foreground) {
		buttonList.forEach(b -> b.setForeground(foreground));
	}

	public void clearSelection() {
		buttonList.forEach(b -> { b.setSelected(false); });
	}
	
	public String getValue() {
		return buttonList.stream().map(b -> b.getName()).collect(Collectors.joining(","));
	}
}
