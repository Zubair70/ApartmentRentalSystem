package model;

import model.types.PropertyType;

public class ParkingSpace extends Property {
    private Area size;
    private int capacity;

    public ParkingSpace(PropertyType type, Area size, int capacity) {
        super(type);
        this.size = size;
        this.capacity = capacity;
    }

    public ParkingSpace() {
    }

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
