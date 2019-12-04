package data;


public class ParkingDeck extends ParkingLocation {

    private ParkingSpace[][] spaces;
    private String locName;
    private int pricePerSpace;

    public ParkingDeck(ParkingSpace[][] spaces, String locName, String locGoogleName, int pricePerSpace) {
        this.spaces = spaces;
        this.locName = locName;
        this.locGoogleName = locGoogleName;
        this.pricePerSpace = pricePerSpace;
    }

    public ParkingDeck(String locName, String locGoogleName, int pricePerSpace, int levels, int totalSpaces, int handiCapped, int staff, int visitors) {
        this.locName = locName;
        this.locGoogleName = locGoogleName;
        this.pricePerSpace = pricePerSpace;
        spaces = new ParkingSpace[levels][totalSpaces / levels];
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j] = new ParkingSpace(pricePerSpace, locName);
            }
        }
        for (int i = 0; i < handiCapped; i++) {
            spaces[0][i].setRestrictions(Restrictions.HANDICAPPED);
        }
        for (int i = handiCapped; i < handiCapped + staff; i++) {
            spaces[0][i].setRestrictions(Restrictions.STAFF);
        }
        if(handiCapped + staff + visitors < spaces[0].length) {
        	for (int i = staff + handiCapped; i < staff + handiCapped + visitors; i++) {
                spaces[0][i].setRestrictions(Restrictions.VISITORS);
            }
        }else {
        	/**int count = 0;
        	for(int i = 1; i < spaces.length; i++) {
        		for(int j = 0; (j < spaces[i].length && count <= visitors); i++) {
        			count++;
        			spaces[i][j].setRestrictions(Restrictions.VISITORS);
        		}
        	}*/
        	int count = 0;
        	for(int i = 0; i < spaces.length; i++) {
        		if(i == 0) {
        			for(int j = handiCapped + staff; (j < spaces[i].length && count <= visitors); j++) {
            			count++;
            			spaces[i][j].setRestrictions(Restrictions.VISITORS);
            		}
        		}
        	
        		for(int j = 0; (j < spaces[i].length && count <= visitors); j++) {
        			count++;
        			spaces[i][j].setRestrictions(Restrictions.VISITORS);
        		}
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
