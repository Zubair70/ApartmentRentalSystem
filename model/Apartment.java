package model;

import model.types.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class Apartment extends Property {
    private final List<Room> ROOMS;

    public Apartment(PropertyType type) {
        super(type);
        ROOMS = new ArrayList<>();
    }

    public Apartment() {
        ROOMS = new ArrayList<>();
    }

    public void addRoom(Room room) {
        ROOMS.add(room);
    }

    public void removeRoom(Room room) {
        ROOMS.removeIf(letter -> letter.getId() == room.getId());
    }

    public Room getRoomByIndex(int index) {
        return ROOMS.get(index);
    }

    public Room getRoomById(int id) {
        return ROOMS.stream()
                .filter(letter -> letter.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(ROOMS);
    }

}
