package app.dash;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppService;
import app.AppView;
import gui.panel.button.ButtonPanel;

public class DashBoardList extends AppView{
	private DashBoard dash;

	public DashBoardList(DashBoard dash) {
		this.dash = dash;
		List<String> result = AppService.getInstance().sql().selectOne("select id, password from members where id = 'ydk'");
		JPanel panel = new JPanel();

		panel.add(new JLabel("DASHBOARDLIST"));
		JTextField textField = new JTextField(10);
		textField.setText(result.get(0));
		panel.add(textField);

		panel.add(new JLabel("DASHBOARDLIST"));
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(passwordField);
		passwordField.setText(result.get(1));
		
		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.addButton("OPEN WRITE",b->dash.openWrite());

		panel.add(buttonPanel);
		rootPanel.add(panel);
	}
	
	public boolean validate() {
		return false;
	}
}
