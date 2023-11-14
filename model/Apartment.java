package model;

import model.types.PropertyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates objects of Apartment to be rented
 */
public class Apartment extends Property {
    /**
     * Rooms in an apartment
     */
    private final List<Room> ROOMS;

    public Apartment(PropertyType type) {
        super(type);
        ROOMS = new ArrayList<>();
    }

    public Apartment() {
        ROOMS = new ArrayList<>();
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
     * Creates new arraylist everytime when called, so that, reference address must not match the original list
     * @return Returns the list of rooms in apartment.
     */
    public List<Room> getRooms() {
        return new ArrayList<>(ROOMS);
    }

}
