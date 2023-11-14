package model;

import model.types.VehicleType;

/**
 * Creates object of Motorcycle vehicle type
 */
public class Motorcycle extends Vehicle{

    /**
     * tells if the motorcycle is a heavy bike
     */
    private boolean isHeavyBike;

    /**
     * engine size i.e. 150 CC or 180CC
     */
    private String engineSize;

    public Motorcycle(String name, VehicleType type, boolean isHeavyBike, String engineSize) {
        super(name, type);
        this.isHeavyBike = isHeavyBike;
        this.engineSize = engineSize;
    }

    public Motorcycle() {
    }

    //Setters and Getters
    public boolean isHeavyBike() {
        return isHeavyBike;
    }

    public void setHeavyBike(boolean heavyBike) {
        isHeavyBike = heavyBike;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }
}
