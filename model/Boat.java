package model;

import model.types.VehicleType;

/**
 * Creates object of boat vehicle type
 */
public class Boat extends Vehicle{
    /**
     * tells if the boat is with or without engine
     */
    private boolean isWithEngine;

    public Boat(String name, VehicleType type, Area area, boolean isWithEngine) {
        super(name, type, area);
        this.isWithEngine = isWithEngine;
    }

    public Boat() {
    }

    //Setters and Getters
    public boolean isWithEngine() {
        return isWithEngine;
    }

    public void setWithEngine(boolean withEngine) {
        isWithEngine = withEngine;
    }
}
