package entity;

public class TicketDTO {
	private String customerName; // 고객 이름 -ticketingView 0
	private String customerId; // 고객 아이디 -ticketingView 0
	private String seatNumber; // seatRow + SeatCol
	private String airNum; // 비행기 번호 -ticketingView 0
	private String reserveDate; // 예약날짜
	private String depDate;
	private int cost; // 티켓 가격 -ticketingView 0
	private int kidCnt;
	private int adultCnt;
	private int HumanCnt; // 인원 수 -ticketingView 0
	public TicketDTO() {}
	public TicketDTO(String customerName, String customerId, String seatNumber, String airNum, String reserveTime,
			String reserveDate, String depDate, int cost, int kidCnt, int adultCnt, int humanCnt) {
		this.customerName = customerName;
		this.customerId = customerId;
		this.seatNumber = seatNumber;
		this.airNum = airNum;
		this.reserveDate = reserveDate;
		this.depDate = depDate;
		this.cost = cost;
		this.kidCnt = kidCnt;
		this.adultCnt = adultCnt;
		this.HumanCnt = humanCnt;
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
	public String getAirNum() {
		return airNum;
	}
	public void setAirNum(String airNum) {
		this.airNum = airNum;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getKidCnt() {
		return kidCnt;
	}
	public void setKidCnt(int kidCnt) {
		this.kidCnt = kidCnt;
	}
	public int getAdultCnt() {
		return adultCnt;
	}
	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}
	public int getHumanCnt() {
		return getKidCnt() + getAdultCnt();
	}
	public void setHumanCnt(int humanCnt) {
		HumanCnt = humanCnt;
	}
}