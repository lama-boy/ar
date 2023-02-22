package entity;

public class AirplaneDTO {
	private int airNum;
	private String seatGrade;
	private String depPlace;
	private String depDate;
	private String arrPlace;
	private String arrTime;
	private String adultCnt;
	private String kidCnt;
	private int humanCnt;
	public AirplaneDTO() {}
	public AirplaneDTO(int airNum, String seatGrade, String depPlace, String depDate, String arrPlace, String arrTime,
			String adultCnt, String kidCnt, int humanCnt) {
		super();
		this.airNum = airNum;
		this.seatGrade = seatGrade;
		this.depPlace = depPlace;
		this.depDate = depDate;
		this.arrPlace = arrPlace;
		this.arrTime = arrTime;
		this.adultCnt = adultCnt;
		this.kidCnt = kidCnt;
		this.humanCnt = humanCnt;
	}
	public int getAirNum() {
		return airNum;
	}
	public void setAirNum(int airNum) {
		this.airNum = airNum;
	}
	public String getSeatGrade() {
		return seatGrade;
	}
	public void setSeatGrade(String seatGrade) {
		this.seatGrade = seatGrade;
	}
	public String getDepPlace() {
		return depPlace;
	}
	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public String getArrPlace() {
		return arrPlace;
	}
	public void setArrPlace(String arrPlace) {
		this.arrPlace = arrPlace;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getAdultCnt() {
		return adultCnt;
	}
	public void setAdultCnt(String adultCnt) {
		this.adultCnt = adultCnt;
	}
	public String getKidCnt() {
		return kidCnt;
	}
	public void setKidCnt(String kidCnt) {
		this.kidCnt = kidCnt;
	}
	public int getHumanCnt() {
		return Integer.parseInt(adultCnt) + Integer.parseInt(kidCnt);
	}
	public void setHumanCnt(int humanCnt) {
		this.humanCnt = humanCnt;
	}
}