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
public class Driver {
    private int licensePlate;
    private ParkingPass parkingPass;

    public Driver(int licensePlate, ParkingPass groups) {
        this.licensePlate = licensePlate;
        this.parkingPass = groups;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public ParkingPass getParkingPass() {
        return parkingPass;
    }
    
    
    
}
