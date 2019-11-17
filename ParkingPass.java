/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

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

    public ArrayList<String> getValidLots() {
        return validLots;
    }

    public ArrayList<Restrictions> getGroups() {
        return groups;
    }
    
    
}
