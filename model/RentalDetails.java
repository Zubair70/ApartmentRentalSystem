package model;

import java.util.Date;

public class RentalDetails {
    private int id;

    private Date startDate;

    private Date endDate;

    private Person person;

    private Property property;

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
