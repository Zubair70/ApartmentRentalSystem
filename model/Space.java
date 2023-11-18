package model;

import java.util.Date;

public abstract class Space {
    /**
     * unique identifier
     */
    private int id;
    /**
     * size of the space i.e. in terms of volume or dimension
     */
    private Area size;

    private boolean isRented;

    /**
     * lease start date
     */
    private Date startDate;

    /**
     * lease end date
     */
    private Date endDate;

    /**
     * id counter to generate unique id on object creation
     */
    private static int idCounter = 0;

    /**
     * creates new object and initializes instance variable
     *
     * @param size      size of the space
     * @param isRented  tells if the room is rented or not
     * @param startDate lease start date
     * @param endDate lease end date
     */
    public Space(Area size, boolean isRented, Date startDate, Date endDate) {
        id = ++idCounter;
        this.size = size;
        this.isRented = isRented;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Space() {
    }

    //Setters and Getters
    public void setId(int id) {
        idCounter += id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Area getSize() {
        return size;
    }

    public void setSize(Area size) {
        this.size = size;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
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
}
