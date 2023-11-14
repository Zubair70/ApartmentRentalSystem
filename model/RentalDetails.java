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
    private Property property;

    /**
     * counter to generate unique id on object creation
     */
    private static int rentalDetailsIdCounter = 0;

    public RentalDetails(Date startDate, Date endDate, Person person, Property property) {
        id = ++rentalDetailsIdCounter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.property = property;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
