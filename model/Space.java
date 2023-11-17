package model;

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
     * id counter to generate unique id on object creation
     */
    private static int idCounter = 0;

    /**
     * creates new object and initializes instance variable
     * @param size size of the space
     * @param isRented tells if the room is rented or not
     */
    public Space(Area size, boolean isRented) {
        id = ++idCounter;
        this.size = size;
        this.isRented = isRented;
    }

    public Space() {
    }

    //Setters and Getters
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
}
