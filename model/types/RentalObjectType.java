package model.types;

/**
 * Types of properties that can be rented
 */
public enum RentalObjectType {
    APARTMENT("Apartment"), PARKING_SPACE("Parking Space");

    private final String type;

    RentalObjectType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
