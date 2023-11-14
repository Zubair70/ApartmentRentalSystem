package model;

public abstract class Area {
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
