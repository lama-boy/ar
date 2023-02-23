package gui.panel;

import gui.panel.input.InputComponent;

public abstract class InputPanel extends CustomPanel implements InputComponent{
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
