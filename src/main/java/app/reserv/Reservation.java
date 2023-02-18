package app.reserv;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.SubApp;
import app.AppView;

public class Reservation extends SubApp {
	private ReservationView reservationView = new ReservationView(this);
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Reservation r = new Reservation();
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(true);
	}

	@Override
	public AppView requestView() {
		return reservationView;
	}
}