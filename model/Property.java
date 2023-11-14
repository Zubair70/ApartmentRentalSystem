package model;

import model.types.PropertyType;

/**
 * Parent class of property types that can be rented i.e. Apartment or ParkingSpace
 */
public abstract class Property {
    /**
     * unique identifier
     */
    private int id;

    /**
     * type of the property
     */
    private PropertyType type;

    /**
     * counter to generate unique id on object creation
     */
    private static int propertyIdCounter = 0;

    public Property(PropertyType type) {
        id = ++propertyIdCounter;
        this.type = type;
    }

    public Property() {
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }
}
