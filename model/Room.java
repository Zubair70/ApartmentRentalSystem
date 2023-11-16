package model;

import java.util.ArrayList;
import java.util.List;

/**
 * to create a room object
 */
public class Room {
    /**
     * unique identifier of the room
     */
    private int id;
    /**
     * size of the room i.e. in terms of volume or dimension
     */
    private Area size;
    /**
     * how many people, this room can accommodate
     */
    private int capacity;

    private boolean isRented;

    /**
     * people who have rented the room
     */
    private final List<Person> RENTAL_PERSONS;

    /**
     * room id counter to generate unique id on object creation
     */
    private static int roomIdCounter = 0;

    /**
     * creates new object and initializes instance variable
     * @param size size of the room
     * @param capacity how many things room can accommodate
     * @param isRented tells if the room is rented or not
     */
    public Room(Area size, int capacity, boolean isRented) {
        id = ++roomIdCounter;
        this.size = size;
        this.capacity = capacity;
        this.isRented = isRented;
        RENTAL_PERSONS = new ArrayList<>();
    }

    /**
     * to create objects, used during program run
     */
    public Room() {
        RENTAL_PERSONS = new ArrayList<>();
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    /**
     * Adds a person in the apartment
     * @param person person object to be added
     */
    public void addPerson(Person person) {
        RENTAL_PERSONS.add(person);
    }

    /**
     * Removes a person object from apartment
     * @param person person object to be removed
     */
    public void removePerson(Person person) {
        RENTAL_PERSONS.removeIf(r -> r.getId() == person.getId());
    }

    /**
     * Returns a person object filtered by index
     * @param index index of the person object
     * @return object of the person
     */
    public Person getPersonByIndex(int index) {
        return RENTAL_PERSONS.get(index);
    }

    /**
     * Returns a person object filtered by its unique identifier
     * @param id unique identifier of the person object
     * @return object of the person
     */
    public Person getPersonById(int id) {
        return RENTAL_PERSONS.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of rental persons in apartment.<br>
     * Creates new arraylist everytime when called, so that, original list can't be changed
     * @return Returns the list of rental persons in apartment.
     */
    public List<Person> getPersons() {
        return new ArrayList<>(RENTAL_PERSONS);
    }
}
