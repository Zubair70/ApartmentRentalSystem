package model;

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
     * how many things, this room can accommodate
     */
    private int capacity;

    /**
     * room id counter to generate unique id on object creation
     */
    private static int roomIdCounter = 0;

    /**
     * creates new object and initializes instance variable
     * @param size size of the room
     * @param capacity how many things room can accommodate
     */
    public Room(Area size, int capacity) {
        id = ++roomIdCounter;
        this.size = size;
        this.capacity = capacity;
    }

    /**
     * to create objects, used during program run
     */
    public Room() {
    }

    //Setters and Getters for private instance variables
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
