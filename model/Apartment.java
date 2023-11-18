package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Creates objects of Apartment to be rented
 */
public class Apartment extends Space {
    /**
     * floor number of apartment
     */
    private int floor;

    /**
     * Person rented by
     */
    private Person rentedBy;

    public Apartment(Area area, boolean isRented, Date startDate, Date endDate, int floor, Person rentedBy) {
        super(area, isRented, startDate, endDate);
        this.floor = floor;
        this.rentedBy = rentedBy;
    }

    public Apartment() {
    }

    //Setters and Getters
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Person getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(Person rentedBy) {
        this.rentedBy = rentedBy;
    }
}
