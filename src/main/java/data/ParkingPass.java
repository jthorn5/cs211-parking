package data;

import java.util.ArrayList;

/**
 *
 * @author marianaritchie
 */
public class ParkingPass {
    private ArrayList<String> validLots;
    private ArrayList<Restrictions> groups;

    public ParkingPass(ArrayList<String> validLots, ArrayList<Restrictions> groups) {
        this.validLots = validLots;
        this.groups = groups;
    }
    
    public ParkingPass() {
        this.validLots = new ArrayList<String>();
        this.groups = new ArrayList<Restrictions>();
    }

    public ArrayList<String> getValidLots() {
        return validLots;
    }

    public ArrayList<Restrictions> getGroups() {
        return groups;
    }
}
