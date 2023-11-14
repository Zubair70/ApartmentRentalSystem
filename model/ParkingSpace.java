package model;

import model.types.PropertyType;

/**
 * Creates objects of ParkingSpace to be rented
 */
public class ParkingSpace extends Property {

    /**
     * size of the parking space in dimension or volume
     */
    private Area size;

    /**
     * number of objects this parking space can accomodate
     */
    private int capacity;

    public ParkingSpace(PropertyType type, Area size, int capacity) {
        super(type);
        this.size = size;
        this.capacity = capacity;
    }

    public ParkingSpace() {
    }

    //Setters and Getters
    public Area getSize() {
        return size;
    }

    public void setSize(Area size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
