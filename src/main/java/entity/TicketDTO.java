package entity;

public class TicketDTO {
	private String customerName; // 고객 이름
	private String customerId; // 고객 아이디
	private String seatNumber; // seatRow + SeatCol
	private String seatGrade; // 좌석등급
	private String airNum; // 비행기 번호
	private String depPlace; // 출발지
	private String arrPlace; // 도착지
	private String depDate; // 출발날짜
	private String arrDate; // 도착날짜
	private String reserveDate; // 예약날짜
	private int cost; // 티켓 가격
	private int kidCnt;
	private int adultCnt;
	private int humanCnt; // 인원 수
	public TicketDTO() {}
	public TicketDTO(String customerName, String customerId, String seatNumber, String seatGrade, String airNum, String reserveTime,
			String depPlace, String arrPlace,
			String reserveDate, String depDate, String arrDate, int cost, int kidCnt, int adultCnt, int humanCnt) {
		this.customerName = customerName;
		this.customerId = customerId;
		this.seatNumber = seatNumber;
		this.seatGrade = seatGrade;
		this.airNum = airNum;
		this.depPlace = depPlace;
		this.arrPlace = arrPlace;
		this.depDate = depDate;
		this.arrDate = arrDate;
		this.reserveDate = reserveDate;
		this.cost = cost;
		this.kidCnt = kidCnt;
		this.adultCnt = adultCnt;
		this.humanCnt = humanCnt;
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
	public String getSeatGrade() {
		return seatGrade;
	}
	public void setSeatGrade(String seatGrade) {
		this.seatGrade = seatGrade;
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
	public String getDepPlace() {
		return depPlace;
	}
	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}
	public String getArrPlace() {
		return arrPlace;
	}
	public void setArrPlace(String arrPlace) {
		this.arrPlace = arrPlace;
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
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
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
		humanCnt = adultCnt + kidCnt;
		return humanCnt;
	}
	public void setHumanCnt(int humanCnt) {
		this.humanCnt = humanCnt;
	}
	@Override
	public String toString() {
		return "TicketDTO [customerName=" + customerName + ", customerId=" + customerId + ", seatNumber=" + seatNumber
				+ ", seatGrade=" + seatGrade
				+ ", airNum=" + airNum + ", depPlace=" + depPlace + ", arrPlace=" + arrPlace + ", depDate=" + depDate
				+ ", arrDate=" + arrDate + ", reserveDate=" + reserveDate + ", cost=" + cost + ", kidCnt=" + kidCnt
				+ ", adultCnt=" + adultCnt + ", humanCnt=" + humanCnt + "]";
	}
	
	
}