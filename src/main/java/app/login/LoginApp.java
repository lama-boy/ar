package app.login;

import static test.Debug.sysout;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppService;
import app.SubApp;
import entity.Member;
import gui.input.button.ButtonPanel;

public class LoginApp extends SubApp {
	public LoginApp() {
		initComponent();
	}
	
	private void initComponent() {
		List<String> result = AppService.getInstance().sql().selectOne("select id, password from members where id = 'ydk'");
		JPanel panel = getPanel();

		panel.add(new JLabel("Login ID"));
		JTextField textField = new JTextField(10);
		textField.setText(result.get(0));
		panel.add(textField);

		panel.add(new JLabel("PW"));
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(passwordField);
		passwordField.setText(result.get(1));
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.addButton("Login",b->login());
		buttonPanel.addButton("Join", b->join());

		panel.add(buttonPanel.getPanel());
	}

	@Override
	public String getIconName() {
		return "heartBlack.png";
	}

	private void join() {
		sysout("join");
	}

	private void login() {
		sysout("login");
		AppService.getInstance().setMember(new Member());
	}
	
	//** 테스트 용 main 입니다. 프로젝트에는 rootPane 만을 사용합니다. **//
	public static void main(String[] args) {
		LoginApp loginApp = new LoginApp();
		
	}
}
