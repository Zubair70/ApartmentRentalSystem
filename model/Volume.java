package model;

/**
 * Volume area type for the size of the room or parking space
 */
public class Volume extends Area {

    /**
     * value of the volume i.e. 330 cubic meters
     */
    private double value;

    public Volume(String type, double value) {
        super(type);
        this.value = value;
    }

    public Volume() {
    }

    //Setter and Getter
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " cubic meters";
    }
}
