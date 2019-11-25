package unused;

public class ParkingPass_CD {
	private String parkingLot;
	private String typeOfDriver;
	
	public ParkingPass_CD() {
		this.parkingLot = "General";
		this.typeOfDriver = "Student";
	}
	
	public ParkingPass_CD(String parkingLot, String typeOfDriver) {
		this.parkingLot = parkingLot;
		this.typeOfDriver = typeOfDriver;
	}
	
	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	public void setTypeOfDriver(String typeOfDriver) {
		this.typeOfDriver = typeOfDriver;
	}
	
	public String getParkingLot() {
		return this.parkingLot;
	}
	
	public String getTypeOfDriver() {
		return this.typeOfDriver;
	}
}
