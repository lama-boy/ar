package app.reserv;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.SubApp;
import dao.DAO;
import app.AppView;
import gui.Gui;
import gui.panel.button.ButtonPanel;

public class ReserveTicket extends AppView {

	private Reservation reserv;
	
	public ReserveTicket(SubApp parentApp) {
		super("reServation뷰", parentApp);
		// TODO Auto-generated constructor stub
	}

	@Override
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
		buttonPanel.addButton("make",b->reserv.reserve(null));

		panel.add(buttonPanel);
		rootPanel.add(panel);
		return rootPanel;
	}
}