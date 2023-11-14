package model;

import model.types.VehicleType;

public class Motorcycle extends Vehicle{
    private boolean isHeavyBike;
    private String engineSize;

    public Motorcycle(String name, VehicleType type, boolean isHeavyBike, String engineSize) {
        super(name, type);
        this.isHeavyBike = isHeavyBike;
        this.engineSize = engineSize;
    }

    public Motorcycle() {
    }

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
