package model;

import java.util.ArrayList;
import java.util.List;

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
     * Rooms in an apartment
     */
    private List<Room> ROOMS;

    /**
     * counter to generate unique id on object creation
     */
    private static int apartmentIdCounter = 0;

    public Apartment(Area area, int floor, boolean isRented) {
        id = ++apartmentIdCounter;
        this.area = area;
        this.floor = floor;
        this.isRented = isRented;
        ROOMS = new ArrayList<>();
    }

    public Apartment() {
        ROOMS = new ArrayList<>();
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

    /**
     * Adds a room in the apartment
     * @param room room object to be added
     */
    public void addRoom(Room room) {
        ROOMS.add(room);
    }

    /**
     * Removes a room object from apartment
     * @param room room object to be removed
     */
    public void removeRoom(Room room) {
        ROOMS.removeIf(r -> r.getId() == room.getId());
    }

    /**
     * Returns a room object filtered by index
     * @param index index of the room object
     * @return object of the room
     */
    public Room getRoomByIndex(int index) {
        return ROOMS.get(index);
    }

    /**
     * Returns a room object filtered by its unique identifier
     * @param id unique identifier of the room object
     * @return object of the room
     */
    public Room getRoomById(int id) {
        return ROOMS.stream()
                .filter(room -> room.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of rooms in apartment.<br>
     * Creates new arraylist everytime when called, so that, original list can't be changed
     * @return Returns the list of rooms in apartment.
     */
    public List<Room> getRooms() {
        return new ArrayList<>(ROOMS);
    }
}
