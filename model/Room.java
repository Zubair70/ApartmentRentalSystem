package model;

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

    /**
     * people who have rented the room
     */
    private List<Person> rentalPersons;

    /**
     * room id counter to generate unique id on object creation
     */
    private static int roomIdCounter = 0;

    /**
     * creates new object and initializes instance variable
     * @param size size of the room
     * @param capacity how many things room can accommodate
     */
    public Room(Area size, int capacity, List<Person> rentalPersons) {
        id = ++roomIdCounter;
        this.size = size;
        this.capacity = capacity;
        this.rentalPersons = rentalPersons;
    }

    /**
     * to create objects, used during program run
     */
    public Room() {
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

    public static int getRoomIdCounter() {
        return roomIdCounter;
    }

    public static void setRoomIdCounter(int roomIdCounter) {
        Room.roomIdCounter = roomIdCounter;
    }
}
