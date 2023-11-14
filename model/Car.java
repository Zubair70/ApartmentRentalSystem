package model;

import model.types.GasType;
import model.types.VehicleType;

public class Car extends Vehicle {
    private String model;
    private int seatingCapacity;
    private String color;
    private GasType gasType;

    public Car(String name, VehicleType type, String model, int seatingCapacity, String color, GasType gasType) {
        super(name, type);
        this.model = model;
        this.seatingCapacity = seatingCapacity;
        this.color = color;
        this.gasType = gasType;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public GasType getGasType() {
        return gasType;
    }

    public void setGasType(GasType gasType) {
        this.gasType = gasType;
    }
}
