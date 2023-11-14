package model.types;

/**
 * Types of properties that can be rented
 */
public enum PropertyType {
    APARTMENT("Apartment"), PARKING_SPACE("Parking Space");

    private final String type;

    PropertyType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
