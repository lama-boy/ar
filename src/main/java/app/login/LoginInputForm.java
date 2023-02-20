package app.login;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppView;
import app.SubApp;
import dao.DAO;
import gui.panel.button.ButtonPanel;

public class LoginInputForm extends AppView{
	private LoginApp login;

	public LoginInputForm(SubApp parentApp) {
		super("로그인인풋폼", parentApp);
	}

	public JPanel initRootPanel() {
		rootPanel.removeAll();
		List<String> result = DAO.sql.selectOne("select * from members where id='ydk'");

		JPanel panel = new JPanel();

		panel.add(new JLabel("Login ID"));
		JTextField textField = new JTextField(10);
		textField.setText(result.get(0));
		panel.add(textField);

		panel.add(new JLabel("PW"));
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(passwordField);
		passwordField.setText(result.get(1));
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.addButton("Login",b->login.login());
		buttonPanel.addButton("Join", b->login.join());

		panel.add(buttonPanel);
		rootPanel.add(panel);
		return rootPanel;
	}

	public boolean validate() {
		return false;
	}
}
