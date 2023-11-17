package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates objects of ParkingSpace to be rented
 */
public class ParkingSpace extends Space {

    /**
     * vehicle stored in the parking space
     */
    private Vehicle vehicle;

    /**
     * Person rented by
     */
    private Person rentedBy;

    /**
     * item(s) stored in the parking space
     */
    private List<Item> ITEMS;

    public ParkingSpace(Area size, boolean isRented, Vehicle vehicle, Person rentedBy) {
        super(size, isRented);
        this.vehicle = vehicle;
        this.rentedBy = rentedBy;
        ITEMS = new ArrayList<>();
    }

    public ParkingSpace() {
        ITEMS = new ArrayList<>();
    }

    //Setters and Getters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(Person rentedBy) {
        this.rentedBy = rentedBy;
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
