package app.admin;

import app.AppService;
import app.AppView;
import app.SubApp;

public class AdminApp extends SubApp{
	private AdminPage adminPage = new AdminPage("AdminApp", this);
	private AddPackage addPackage = new AddPackage(this);
	
	@Override
	public AppView requestView() {
		// TODO Auto-generated method stub
		return adminPage;
	}

	public void openPackage() {
		AppService.getInstance().openView(addPackage);
	}
}
