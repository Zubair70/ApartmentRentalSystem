package model.types;

public enum VehicleType {
    OFF_ROAD_CAR("Off-Road Car"), CITY_CAR("City Car"), BOAT("Boat"), MOTORCYCLE("Motorcycle"), AMPHIBIOUS_CAR("Amphibious Car");
    private String type;

    private VehicleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
