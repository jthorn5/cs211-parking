/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

/**
 *
 * @author marianaritchie
 */
public class ParkingDeck extends ParkingLocation implements ParkingAvailability {

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
        for (int i = handiCapped; i < staff; i++) {
            spaces[0][i].setRestrictions(Restrictions.STAFF);
        }
        for (int i = staff; i < visitors; i++) {
            spaces[0][i].setRestrictions(Restrictions.VISITORS);
        }
    }

    @Override
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
