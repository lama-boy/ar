package app.reserv;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.SubApp;
import app.AppView;
import gui.Gui;

public class ReservationView extends AppView {
	private Reservation reserv;
	
	public ReservationView(Reservation reserv) {
		this.reserv = reserv;
		setImageIcon(Gui.getResizedIcon(reserv.getImagePath(), 35, 35));
		initComponent();
	}
	
	private void initComponent() {
	}
	
	@Override
	public String getTitle() {
		return "Reservation";
	}
}