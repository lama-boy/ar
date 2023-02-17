package app.reserv;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.SubApp;

public class Reservation extends SubApp {
	public Reservation() {
		initComponent();
	}
	
	private void initComponent() {
		JPanel panel = getPanel();
		panel.add(new JLabel("Reservation"));
		JTextField textField = new JTextField(10);
		panel.add(textField);
	}
	
	@Override
	public String getTitle() {
		return "Reservation";
	}

	@Override
	public String getIconName() {
		return "heart.png";
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Reservation r = new Reservation();
		try {
			frame.setContentPane(r.rootPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(true);
	}
}