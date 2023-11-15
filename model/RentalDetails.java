package model;

import java.util.Date;

public class RentalDetails {

    /**
     * unique identifier
     */
    private int id;

    /**
     * rental start date
     */
    private Date startDate;

    /**
     * rental end date
     */
    private Date endDate;

    /**
     * person to whom property has been rented
     */
    private Person person;

    /**
     * property that has been rented
     */
    private RentalObject rentalObject;

    /**
     * counter to generate unique id on object creation
     */
    private static int rentalDetailsIdCounter = 0;

    public RentalDetails(Date startDate, Date endDate, Person person, RentalObject rentalObject) {
        id = ++rentalDetailsIdCounter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.rentalObject = rentalObject;
    }

    public RentalDetails() {
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public RentalObject getRentalObject() {
        return rentalObject;
    }

    public void setRentalObject(RentalObject rentalObject) {
        this.rentalObject = rentalObject;
    }
}
