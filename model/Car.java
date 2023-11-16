package model;

import model.types.GasType;
import model.types.VehicleType;

/**
 * Creates object of car vehicle type
 */
public class Car extends Vehicle {
    /**
     * model of the car
     */
    private String model;

    /**
     * seating capacity of the car
     */
    private int seatingCapacity;

    /**
     * color the car
     */
    private String color;

    /**
     * Gas type in the car
     */
    private GasType gasType;

    public Car(String name, VehicleType type, Area area, String model, int seatingCapacity, String color, GasType gasType) {
        super(name, type, area);
        this.model = model;
        this.seatingCapacity = seatingCapacity;
        this.color = color;
        this.gasType = gasType;
    }

    public Car() {
    }

    //Setters and Getters
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
