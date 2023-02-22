package app.login.db;

public class AirLineSignUpVO {
	
	private String ID;
	private String PASSWORD;
	private String NAME;
	private String EMAIL;
	private String PHONE;
	private String INDATE;
	private String GENDER;
	
	public AirLineSignUpVO() {}
	public AirLineSignUpVO(String ID, String PASSWORD, 
			String NAME, String EMAIL, String PHONE,String INDATE, String GENDER) {
		this.ID = ID;
		this.PASSWORD = PASSWORD;
		this.NAME = NAME;
		this.EMAIL = EMAIL;
		this.PHONE = PHONE;
		this.INDATE = INDATE;
		this.GENDER = GENDER;
	}
	
	public AirLineSignUpVO(String UID) {
		this.ID = UID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}
	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String PHONE) {
		this.PHONE = PHONE;
	}

	public String getINDATE() {
		return INDATE;
	}

	public void setINDATE(String INDATE) {
		this.INDATE = INDATE;
	}
	
	public String getGENDER() {
		return GENDER;
	}
	
	public void setGENDER(String GENDER) {
		this.GENDER = GENDER;
	}
}