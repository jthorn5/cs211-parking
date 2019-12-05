package data;

public class Driver {
	private ParkingPass pass;
	private String licensePlate;
	/*private boolean onCampus;
	private int gNumber;
	private String fullName;*/
	public Driver() {
		
	}
	
	public Driver(String licensePlate, ParkingPass pass) {//  boolean onCampus, int gNumber, String fullName) {
		this.pass = pass;
		this.licensePlate = licensePlate;
		/*this.onCampus = onCampus;
		this.gNumber = gNumber;
		this.fullName = fullName;*/
		
	}
	public void setPass(ParkingPass pass) {
		this.pass = pass;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public String getLicensePlate() {
		return this.licensePlate;
	}
	
	public ParkingPass getParkingPass() {
		return this.pass;
	}
	
	/*public void setOnCampus(boolean onCampus) {
		this.onCampus = onCampus;
	}
	
	public void setGnumber(int gNumber) {
		this.gNumber = gNumber;
	}
	
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}
	
	public boolean getOnCampus() {
		return this.onCampus;
	}
	
	public int getGnumber() {
		return this.gNumber;
	}
	
	public String getFullName() {
		return this.fullName;
	}*/
}
