package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates objects of ParkingSpace to be rented
 */
public class ParkingSpace {

    private int id;

    private boolean isRented;

    /**
     * size of the parking space in dimension or volume
     */
    private Area size;

    /**
     * vehicle stored in the parking space
     */
    private Vehicle vehicle;

    /**
     * item(s) stored in the parking space
     */
    private List<Item> ITEMS;

    /**
     * counter to generate unique id on object creation
     */
    private static int parkingSpaceIdCounter = 0;

    public ParkingSpace(Area size, Vehicle vehicle, boolean isRented) {
        id = ++parkingSpaceIdCounter;
        this.size = size;
        this.vehicle = vehicle;
        this.isRented = isRented;
        ITEMS = new ArrayList<>();
    }

    public ParkingSpace() {
        ITEMS = new ArrayList<>();
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    /**
     * Adds an item in the apartment
     * @param item Item type object to be added
     */
    public void addItem(Item item) {
        ITEMS.add(item);
    }

    /**
     * Removes an item object from apartment
     * @param item Item type object to be removed
     */
    public void removeItem(Item item) {
        ITEMS.removeIf(i -> i.getId() == item.getId());
    }

    /**
     * Returns an item object filtered by index
     * @param index index of the item object
     * @return object of the item
     */
    public Item getItemByIndex(int index) {
        return ITEMS.get(index);
    }

    /**
     * Returns an item object filtered by its unique identifier
     * @param id unique identifier of the item object
     * @return object of the item
     */
    public Item getItemById(int id) {
        return ITEMS.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of items in apartment.<br>
     * Creates new arraylist everytime when called, so that, original list can't be changed
     * @return Returns the list of items in apartment.
     */
    public List<Item> getItems() {
        return new ArrayList<>(ITEMS);
    }
}
