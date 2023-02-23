package app.reserv;

import app.AppService;
import app.AppView;
import app.SubApp;

public class Reservation extends SubApp {
	private ReservView reservView = new ReservView(this);
	private AirplaneView airplaneView = new AirplaneView(this);  
	private SelectSeat selectSeat = new SelectSeat(this, null);  
	
	public void openReservView() {
		AppService.getInstance().openView(reservView);
	}
	
	public void openAirplaneView() {
		AppService.getInstance().openView(airplaneView);
	}
	
	public void openSeatView() {
		AppService.getInstance().openView(selectSeat);
	}
	
	@Override
	public AppView requestView() {
		return airplaneView;
	}
}