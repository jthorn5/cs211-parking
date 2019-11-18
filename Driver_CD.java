
public class Driver {
	private ParkingPass pass;
	private int licensePlate;
	private boolean onCampus;
	private int gNumber;
	private String fullName;
	public Driver() {
		
	}
	
	public Driver(ParkingPass pass, int licensePlate, boolean onCampus, int gNumber, String fullName) {
		this.pass = pass;
		this.licensePlate = licensePlate;
		this.onCampus = onCampus;
		this.gNumber = gNumber;
		this.fullName = fullName;
		
	}
	public void setPass(ParkingPass pass) {
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
}
