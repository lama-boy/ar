package app.login;

import static test.Debug.sysout;

import app.AppService;
import app.AppView;
import app.SubApp;
import entity.Member;

public class LoginApp extends SubApp {
	private LoginInputForm loginInputForm = new LoginInputForm(this);
	
	public LoginApp() {
		initComponent();
	}
	
	private void initComponent() {
	}

	public void join() {
		sysout("join");
	}

	public void login() {
		sysout("login");
		AppService.getInstance().setMember(new Member());
	}
	
	@Override
	public AppView requestView() {
		return loginInputForm;
	}
	
	public static void main(String[] args) {
		LoginApp loginApp = new LoginApp();
	}
}
