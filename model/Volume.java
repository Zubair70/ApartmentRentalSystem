package model;

public class Volume extends Area {
    private double value;

    public Volume(String type, double value) {
        super(type);
        this.value = value;
    }

    public Volume() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
