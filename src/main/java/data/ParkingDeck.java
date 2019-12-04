package data;


public class ParkingDeck extends ParkingLocation {

    private ParkingSpace[][] spaces;
    private String lotName;
    private int pricePerSpace;

    public ParkingDeck(ParkingSpace[][] spaces, String lotName, int pricePerSpace) {
        this.spaces = spaces;
        this.lotName = lotName;
        this.pricePerSpace = pricePerSpace;
    }

    public ParkingDeck(String lotName, int pricePerSpace, int levels, int totalSpaces, int handiCapped, int staff, int visitors) {
        this.lotName = lotName;
        this.pricePerSpace = pricePerSpace;
        spaces = new ParkingSpace[levels][totalSpaces / levels];
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j] = new ParkingSpace(pricePerSpace, lotName);
            }
        }
        for (int i = 0; i < handiCapped; i++) {
            spaces[0][i].setRestrictions(Restrictions.HANDICAPPED);
        }
        for (int i = handiCapped; i < handiCapped + staff; i++) {
            spaces[0][i].setRestrictions(Restrictions.STAFF);
        }
        if(handiCapped + staff + visitors < spaces[0].hashCode().length) {
        	for (int i = staff + handiCapped; i < staff + handiCapped + visitors; i++) {
                spaces[0][i].setRestrictions(Restrictions.VISITORS);
            }
        }else {
        	for(int i = 0; i < visitors; i ++) {
        		spaces[1][i].setRestrictions(Restrictions.VISITORS);
        	}
        }
        
    }

    public boolean isAvailable(Driver d) {
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {

                if (spaces[i][j].isAvailable(d)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addCar(Driver d) {
        if (isAvailable(d)) {

            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces[i].length; j++) {

                    if (spaces[i][j].isAvailable(d)) {
                        spaces[i][j].setLicenseNum(d.getLicensePlate());
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
