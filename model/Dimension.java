package model;

/**
 * Dimension area type for the size of the room or parking space
 */
public class Dimension extends Area {

    /**
     * length of the room
     */
    private double length;
    /**
     * width of the room
     */
    private double width;
    /**
     * height of the room
     */
    private double height;

    public Dimension(String type, double length, double width, double height) {
        super(type);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Dimension() {
    }

    //Setters and Getters
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

    @Override
    public String toString() {
        return length + "m x" + width + "m x" + height + "m";
    }
}
