package gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import gui.panel.CustomPanel;

public class InputForm<T> {
	
	private Map<String,CustomPanel> map = new HashMap<>();

	public CustomPanel addComp(String name, CustomPanel customPanel) {
		return customPanel;
	}
	
	public void resetForm() {
		
	}

	public void setData(T t) {
		
	}
	
	public String getValue(String key) {
		return null;
	}
	
	public T getData() {
		return null;
	}
}
