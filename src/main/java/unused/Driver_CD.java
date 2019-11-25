package unused;

public class Driver_CD {
	private ParkingPass_CD pass;
	private int licensePlate;
	private boolean onCampus;
	private int gNumber;
	private String fullName;
	public Driver_CD() {
		
	}
	
	public Driver_CD(ParkingPass_CD pass, int licensePlate, boolean onCampus, int gNumber, String fullName) {
		this.pass = pass;
		this.licensePlate = licensePlate;
		this.onCampus = onCampus;
		this.gNumber = gNumber;
		this.fullName = fullName;
		
	}
	public void setPass(ParkingPass_CD pass) {
		this.pass = pass;
	}
	
	public void setLicensePlate(int licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public void setOnCampus(boolean onCampus) {
		this.onCampus = onCampus;
	}
	
	public void setGnumber(int gNumber) {
		this.gNumber = gNumber;
	}
	
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}
	
	public ParkingPass_CD getPass() {
		return this.pass;
	}
	
	public int getLicensePlate() {
		return this.licensePlate;
	}
	
	public boolean getOnCampus() {
		return this.onCampus;
	}
	
	public int getGnumber() {
		return this.gNumber;
	}
	
	public String getFullName() {
		return this.fullName;
	}
}
