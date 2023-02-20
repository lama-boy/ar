package app.reserv;

import app.AppView;
import app.SubApp;
import gui.Gui;

public class Reservation extends SubApp {
	private ReserveTicket reserveTicket;
	
	public Reservation() {
		reserveTicket = new ReserveTicket(this);
		reserveTicket.initRootPanel();
	}
	
	@Override
	public AppView requestView() {
		return reserveTicket;
	}

	public Object reserve(Object object) {
		return null;
	}
	
	public boolean cancel() {
		return false;
	}
	
	public static void main(String[] args) {
		Reservation r = new Reservation();
		Gui.createFrame(r.requestView().getPanel());
	}
}