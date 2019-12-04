package data;

/**
 *
 * @author marianaritchie
 */
public abstract class ParkingLocation {
	
    abstract boolean addCar(Driver d);
    
    abstract public boolean isAvailable(Driver d);
}
