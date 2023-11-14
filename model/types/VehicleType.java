package model.types;

/**
 * Vehicle types that can be parked in parking spaces
 */
public enum VehicleType {
    OFF_ROAD_CAR("Off-Road Car"), CITY_CAR("City Car"), BOAT("Boat"), MOTORCYCLE("Motorcycle"), AMPHIBIOUS_CAR("Amphibious Car");
    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
