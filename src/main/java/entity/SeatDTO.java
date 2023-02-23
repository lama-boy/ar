package entity;

import javax.swing.JCheckBox;

public class SeatDTO extends JCheckBox{
	private String airnum;
	private String seatNumber;
	private String seatGrade;
	private String reserved;
	private String depDate;
	public SeatDTO() {}
	public SeatDTO(String airnum, String seatNumber, String seatGrade, String reserved, String depDate) {
		super();
		this.airnum = airnum;
		this.seatNumber = seatNumber;
		this.seatGrade = seatGrade;
		this.reserved = reserved;
		this.depDate = depDate;
	}
	public String getAirnum() {
		return airnum;
	}
	public void setAirnum(String airnum) {
		this.airnum = airnum;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getSeatGrade() {
		return seatGrade;
	}
	public void setSeatGrade(String seatGrade) {
		this.seatGrade = seatGrade;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
}