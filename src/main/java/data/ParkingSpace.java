package data;

public class ParkingSpace {
    private int price;
    private Restrictions restrictions;
    private String lot;
    private String license;

    public ParkingSpace() {
    	this(0,"");
        this.restrictions = Restrictions.NONE;
        this.license = "";
    }

    public ParkingSpace(int price, Restrictions restrictions, String lot) {
        this(price, restrictions, "", lot);
    }

    public ParkingSpace(int price, String lot) {
    	this(price,Restrictions.NONE,"",lot);
    }

    public ParkingSpace(int price, Restrictions restrictions, String license, String lot) {
        this.price = price;
        this.restrictions = restrictions;
        this.license = license;
        this.lot = lot;
    }
    
    public boolean isAvailable(Driver d) {
        
        if(license.equals("")){
            return true;
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
    
    public String getLicense() {
    	return license;
    }

    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

    public void setLicense(String license) {
        this.license = license;
    }
    
    
    
    
}
