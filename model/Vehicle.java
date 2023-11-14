package model;

import model.types.VehicleType;

public abstract class Vehicle {
    private int id;
    private String name;
    private VehicleType type;

    private static int vehicleIdCounter = 0;

    protected Vehicle(String name, VehicleType type) {
        id = ++vehicleIdCounter;
        this.name = name;
        this.type = type;
    }

    public Vehicle() {
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

    protected void setType(VehicleType type) {
        this.type = type;
    }
}
