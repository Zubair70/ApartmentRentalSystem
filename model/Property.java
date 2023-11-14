package model;

import model.types.PropertyType;

public abstract class Property {
    private int id;
    private PropertyType type;

    private static int propertyIdCounter = 0;

    public Property(PropertyType type) {
        id = ++propertyIdCounter;
        this.type = type;
    }

    public Property() {
    }

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
