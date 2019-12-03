package data;

/**
 *
 * @author marianaritchie
 */
public abstract class ParkingLocation {
	
	int pricePerSpace;
	
	String lotName;
    
    abstract boolean addCar(Driver d);
    
    abstract public boolean isAvailable(Driver d);
}
