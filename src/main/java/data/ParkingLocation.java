package data;

/**
 *
 * @author marianaritchie
 */
public abstract class ParkingLocation {
	
	protected String locName;
	protected String locGoogleName;
    protected int pricePerSpace;
    
    abstract boolean addCar(Driver d);
    
    abstract public boolean isAvailable(Driver d);

	public String getLocName() {
		return locName;
	}

	public String getLocGoogleName() {
		return locGoogleName;
	}
	
}
