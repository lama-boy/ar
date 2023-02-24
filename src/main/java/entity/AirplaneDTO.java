package entity;

public class AirplaneDTO {
	private String airNum;
	private String depPlace;
	private String arrPlace;
	private String depDate;
	private String arrDate;
	private String depTime;
	private String arrTime;
	public String getAirNum() {
		return airNum;
	}
	public void setAirNum(String airNum) {
		this.airNum = airNum;
	}
	public String getDepCountry() {
		return depPlace;
	}
	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
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
	@Override
	public String toString() {
		return "AirplaneDTO [airNum=" + airNum + ", depPlace=" + depPlace + ", depDate=" + depDate + ", arrPlace="
				+ arrPlace + ", arrTime=" + arrTime + "]";
	}

}
