package unused;

import data.ParkingPass;

/**
 *
 * @author marianaritchie
 */
public class Driver_M {
    private int licensePlate;
    private ParkingPass parkingPass;

    public Driver_M(int licensePlate, ParkingPass groups) {
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
