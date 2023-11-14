package model.types;

public enum PropertyType {
    APARTMENT("Apartment"), PARKING_SPACE("Parking Space");

    private String type;

    private PropertyType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
