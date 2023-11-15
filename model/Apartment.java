package model;

/**
 * Creates objects of Apartment to be rented
 */
public class Apartment {

    /**
     * unique id of the apartment
     */
    private int id;

    /**
     * area it occupies
     */
    private Area area;

    /**
     * floor number of apartment
     */
    private int floor;

    /**
     * tells if the apartment is rented or not
     */
    private boolean isRented;

    /**
     * counter to generate unique id on object creation
     */
    private static int apartmentIdCounter = 0;

    public Apartment(Area area, int floor, boolean isRented) {
        id = ++apartmentIdCounter;
        this.area = area;
        this.floor = floor;
        this.isRented = isRented;
    }

    public Apartment() {
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
