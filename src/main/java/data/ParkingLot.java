package data;

/**
 *
 * @author marianaritchie
 */
public class ParkingLot extends ParkingLocation {

	private ParkingSpace[] spaces;

    public ParkingLot(String locName, String locGoogleName, ParkingSpace[] spaces, int pricePerSpace) {
        this.locName = locName;
        this.locGoogleName = locGoogleName;
        this.spaces = spaces;
        this.pricePerSpace = pricePerSpace;
    }

    public ParkingLot(String locName, String locGoogleName, int pricePerSpace, int totalSpaces, int handiCapped, int staff, int visitors) {
        this.locName = locName;
        this.locGoogleName = locGoogleName;
        this.pricePerSpace = pricePerSpace;
        spaces = new ParkingSpace[totalSpaces];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new ParkingSpace(pricePerSpace, locName);
        }
        for (int i = 0; i < handiCapped; i++) {
            spaces[i].setRestrictions(Restrictions.HANDICAPPED);
        }
        for (int i = handiCapped; i < staff + handiCapped; i++) {
            spaces[i].setRestrictions(Restrictions.STAFF);
        }
        for (int i = staff + handiCapped; i < staff + handiCapped + visitors; i++) {
            spaces[i].setRestrictions(Restrictions.VISITORS);
        }
    }

    public boolean addCar(Driver d) {
        if (isAvailable(d)) {
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].isAvailable(d)) {
                    spaces[i].setLicense(d.getLicensePlate());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAvailable(Driver d) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].isAvailable(d)) {
                return true;
            }
        }
        return false;
    }

}
