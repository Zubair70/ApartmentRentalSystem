package model.types;

/**
 * Gas types that are used in Cars as fuel
 */
public enum GasType {
    GASOLINE("Gasoline"), DIESEL("Diesel");

    private final String type;

    GasType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
