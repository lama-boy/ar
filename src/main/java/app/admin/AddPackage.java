package app.admin;

import app.AppView;
import gui.panel.layout.GridBagPanel;

public class AddPackage extends AppView{
	private AdminApp adminApp;
	
	public AddPackage(AdminApp adminApp) {
		this.adminApp = adminApp;
	}
	
	public void initRootPanel() {
		GridBagPanel formPanel = new GridBagPanel();
		formPanel.addNextRow(formPanel);
	}
}
