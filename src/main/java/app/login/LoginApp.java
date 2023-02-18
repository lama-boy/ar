package app.login;

import static test.Debug.sysout;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppService;
import app.SubApp;
import app.AppView;
import entity.Member;
import gui.panel.button.ButtonPanel;

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
