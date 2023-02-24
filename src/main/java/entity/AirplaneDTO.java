package entity;

public class AirplaneDTO {
	private String airNum;
	private String depCountry;
	private String arrCountry;
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
		return depCountry;
	}
	public void setDepCountry(String depCountry) {
		this.depCountry = depCountry;
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
	public String getarrCountry() {
		return arrCountry;
	}
	public void setarrCountry(String arrCountry) {
		this.arrCountry = arrCountry;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	@Override
	public String toString() {
		return "AirplaneDTO [airNum=" + airNum + ", depCountry=" + depCountry + ", depDate=" + depDate + ", arrCountry="
				+ arrCountry + ", arrTime=" + arrTime + "]";
	}

}