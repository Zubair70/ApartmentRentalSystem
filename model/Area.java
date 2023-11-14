package model;

/**
 * Parent class to describe the area type
 */
public abstract class Area {
    /**
     * type of area
     */
    private String type;

    protected Area(String type) {
        this.type = type;
    }

    protected Area() {
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }
}
