package model;

import model.types.RentalObjectType;

/**
 * Creates objects of ParkingSpace to be rented
 */
public class ParkingSpace {

    private int id;

    private boolean isRented;

    /**
     * size of the parking space in dimension or volume
     */
    private Area size;

    /**
     * counter to generate unique id on object creation
     */
    private static int parkingSpaceIdCounter = 0;

    public ParkingSpace(boolean isRented, Area size) {
        id = ++parkingSpaceIdCounter;
        this.isRented = isRented;
        this.size = size;
    }

    public ParkingSpace() {
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Area getSize() {
        return size;
    }

    public void setSize(Area size) {
        this.size = size;
    }

}
