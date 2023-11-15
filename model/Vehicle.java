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
    private Area area;

    /**
     * counter to generate unique id on object creation
     */
    private static int vehicleIdCounter = 0;

    protected Vehicle(String name, VehicleType type, Area area) {
        id = ++vehicleIdCounter;
        this.name = name;
        this.type = type;
        this.area = area;
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

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
