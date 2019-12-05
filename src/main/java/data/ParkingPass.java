package data;

import java.util.ArrayList;


public class ParkingPass {
    private ArrayList<String> validLots;
    private ArrayList<Restrictions> groups;

    public ParkingPass(ArrayList<String> validLots, ArrayList<Restrictions> groups) {
        this.validLots = validLots;
        this.groups = groups;
    }
    
    public ParkingPass(ArrayList<String> validLots, Restrictions r) {
        this.validLots = validLots;
        this.groups = new ArrayList<Restrictions>();
        this.groups.add(r);
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
