package entity;

public class AirplaneDTO {
	private int airNum;
	private String depPlace;
	private String depDate;
	private String arrPlace;
	private String arrTime;
	public int getAirNum() {
		return airNum;
	}
	public void setAirNum(int airNum) {
		this.airNum = airNum;
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
	@Override
	public String toString() {
		return "AirplaneDTO [airNum=" + airNum + ", depPlace=" + depPlace + ", depDate=" + depDate + ", arrPlace="
				+ arrPlace + ", arrTime=" + arrTime + "]";
	}

}