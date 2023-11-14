package model.types;

public enum GasType {
    GASOLINE("Gasoline"), DIESEL("Diesel");

    private String type;

    private GasType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
