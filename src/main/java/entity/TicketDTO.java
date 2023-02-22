package entity;

public class TicketDTO {
	private String customerName; // 고객 이름 -ticketingView 0
	private String customerId; // 고객 아이디 -ticketingView 0
	private String seatNumber; // seatRow + SeatCol
	private int    airNum; // 비행기 번호 -ticketingView 0
	private String reserveTime; // 예약시간
	private String reserveDate; // 예약날짜
	private int cost; // 티켓 가격 -ticketingView 0
	private int person; // 인원 수 -ticketingView 0
	public TicketDTO() {}
	public TicketDTO(String customerName, String customerId, String seatNumber, int airNum,
			String reserveTime,String reserveDate, int cost, int person) {
		this.customerName = customerName;
		this.customerId = customerId;
		this.seatNumber = seatNumber;
		this.airNum = airNum;
		this.reserveTime = reserveTime;
		this.reserveDate = reserveDate;
		this.cost = cost;
		this.person = person;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getairNum() {
		return airNum;
	}
	public void setairNum(int airNum) {
		this.airNum = airNum;
	}
	public String getreserveTime() {
		return reserveTime;
	}
	public void setreserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	} 
}