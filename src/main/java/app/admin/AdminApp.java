package app.admin;

import app.AppView;
import app.SubApp;

public class AdminApp extends SubApp{
	private AdminPage adminPage = new AdminPage("회원정보 리스트", this);
	
	@Override
	public AppView requestView() {
		// TODO Auto-generated method stub
		return adminPage;
	}
}
