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
     * counter to generate unique id on object creation
     */
    private static int vehicleIdCounter = 0;

    protected Vehicle(String name, VehicleType type) {
        id = ++vehicleIdCounter;
        this.name = name;
        this.type = type;
    }

    public Vehicle() {
    }

    //Setters and Getters
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

    protected void setType(VehicleType type) {
        this.type = type;
    }
}
