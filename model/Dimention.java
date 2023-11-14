package model;

public class Dimention extends Area{
    private double length;
    private double width;
    private double height;

    public Dimention(String type, double length, double width, double height) {
        super(type);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Dimention() {
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
