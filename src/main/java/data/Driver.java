package data;

/**
 *
 * @author marianaritchie
 */
public class Driver {
    private int licensePlate;
    private ParkingPass parkingPass;

    public Driver(int licensePlate, ParkingPass groups) {
        this.licensePlate = licensePlate;
        this.parkingPass = groups;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public ParkingPass getParkingPass() {
        return parkingPass;
    }
}
