package model;

import model.types.VehicleType;

/**
 * parent class of the vehicles
 */
public abstract class Vehicle {

    /**
     * unique identifier
     */
    private int id;

    /**
     * name of the vehicle
     */
    private String name;

    /**
     * type of the vehicle
     */
    private VehicleType type;

    /**
     * occupied area of the vehicle
     */
    private Area size;

    /**
     * counter to generate unique id on object creation
     */
    private static int vehicleIdCounter = 0;

    protected Vehicle(String name, VehicleType type, Area size) {
        id = ++vehicleIdCounter;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public Vehicle() {
    }

    //Setters and Getters
    public void setId(int id) {
        vehicleIdCounter += id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Area getSize() {
        return size;
    }

    public void setSize(Area size) {
        this.size = size;
    }
}
