package data;

/**
 *
 * @author marianaritchie
 */
public class ParkingLot extends ParkingLocation implements ParkingAvailability {

    private String lotName;
    private ParkingSpace[] spaces;
    private int pricePerSpace;

    public ParkingLot(String lotName, ParkingSpace[] spaces, int pricePerSpace) {
        this.lotName = lotName;
        this.spaces = spaces;
        this.pricePerSpace = pricePerSpace;
    }

    public ParkingLot(String lotName, int pricePerSpace, int totalSpaces, int handiCapped, int staff, int visitors) {
        this.lotName = lotName;
        this.pricePerSpace = pricePerSpace;
        spaces = new ParkingSpace[totalSpaces];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new ParkingSpace(pricePerSpace, lotName);
        }
        for (int i = 0; i < handiCapped; i++) {
            spaces[i].setRestrictions(Restrictions.HANDICAPPED);
        }
        for (int i = handiCapped; i < staff; i++) {
            spaces[i].setRestrictions(Restrictions.STAFF);
        }
        for (int i = staff; i < visitors; i++) {
            spaces[i].setRestrictions(Restrictions.VISITORS);
        }
    }

    public boolean addCar(Driver d) {
        if (isAvailable(d)) {
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].isAvailable(d)) {
                    spaces[i].setLicenseNum(d.getLicensePlate());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isAvailable(Driver d) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].isAvailable(d)) {
                return true;
            }
        }
        return false;
    }

}
