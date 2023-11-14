package model;

import model.types.VehicleType;

public class Boat extends Vehicle{
    private boolean isWithEngine;

    public Boat(String name, VehicleType type, boolean isWithEngine) {
        super(name, type);
        this.isWithEngine = isWithEngine;
    }

    public Boat() {
    }

    public boolean isWithEngine() {
        return isWithEngine;
    }

    public void setWithEngine(boolean withEngine) {
        isWithEngine = withEngine;
    }
}
