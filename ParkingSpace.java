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
public class ParkingSpace implements ParkingAvailability {
    private int price;
    private Restrictions restrictions;
    private String lot;
    private int licenseNum;

    public ParkingSpace() {
        this.price = 0;
        restrictions = Restrictions.NONE;
        licenseNum = 0;
        lot = "";
    }

    public ParkingSpace(int price, Restrictions restrictions, String lot) {
        this(price, restrictions, 0, lot);
    }

    public ParkingSpace(int price, String lot) {
        this.price = price;
        this.lot = lot;
    }

    public ParkingSpace(int price, Restrictions restrictions, int licenseNum, String lot) {
        this.price = price;
        this.restrictions = restrictions;
        this.licenseNum = licenseNum;
        this.lot = lot;
    }
    
    public boolean isAvailable(Driver d) {
        
        if(licenseNum!=0){
            return false;
        }
        ParkingPass pass = d.getParkingPass();
        if(pass.getGroups().contains(restrictions)&&(pass.getValidLots().contains(lot))){
            return true;
        }
        return false; 
    }

    public int getPrice() {
        return price;
    }

    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

    public void setLicenseNum(int licenseNum) {
        this.licenseNum = licenseNum;
    }
    
    
    
    
}
