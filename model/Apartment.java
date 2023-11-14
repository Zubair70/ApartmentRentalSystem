package model;

import model.types.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class Apartment extends Property {
    private List<Room> rooms;

    public Apartment(PropertyType type) {
        super(type);
        new ArrayList<>();
    }

    public Apartment() {
        new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.removeIf(letter -> letter.getId() == room.getId());
    }

    public Room getRoomByIndex(int index) {
        return rooms.get(index);
    }

    public Room getRoomById(int id) {
        return rooms.stream()
                .filter(letter -> letter.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

}
