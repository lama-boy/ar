package app.reserv;

import app.AppService;
import app.AppView;
import app.SubApp;
import entity.TicketDTO;

public class Reservation extends SubApp {
	private ReservView reservView = new ReservView(this);
	private AirplaneView airplaneView = new AirplaneView(this);  
	private SelectSeat selectSeat = new SelectSeat(this, null);  
    //private Payment payment = new Payment(this);  // 결제페이지 연결
	
//	public void openPayment(TicketDTO ticketDTO) { // 결제페이지 불러오기
//		AppService.getInstance().openView(payment);
//		System.out.println(ticketDTO);
//	}
	public void openReservView() {
		AppService.getInstance().openView(reservView);
	}
	
	public void openAirplaneView() {
		AppService.getInstance().openView(airplaneView);
	}
	
	public void openSeatView(TicketDTO ticketDTO) {
		AppService.getInstance().openView(selectSeat);
	}
	
	@Override
	public AppView requestView() {
		return airplaneView;
	}
}