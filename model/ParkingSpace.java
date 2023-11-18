package model;

import exception.TooManyThingsException;

import java.util.ArrayList;
import java.util.Date;
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

    public ParkingSpace(Area size, boolean isRented, Date startDate, Date endDate, Vehicle vehicle, Person rentedBy) {
        super(size, isRented, startDate, endDate);
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
    public void addItem(Item item) throws TooManyThingsException {
        double totalOccupiedVolume = getTotalOccupiedVolume();
        double itemVolume = 0;
        if(item.getSize().getType().equals("Volume")) {
            itemVolume += ((Volume) item.getSize()).getValue();
        } else {
            itemVolume += ((Dimension) item.getSize()).getLength()
                    * ((Dimension) item.getSize()).getWidth()
                    * ((Dimension) item.getSize()).getHeight();
        }

        double parkingSpaceVolume = 0;
        if(getSize().getType().equals("Volume")) {
            itemVolume += ((Volume) getSize()).getValue();
        } else {
            itemVolume += ((Dimension) getSize()).getLength()
                    * ((Dimension) getSize()).getWidth()
                    * ((Dimension) getSize()).getHeight();
        }
        if((totalOccupiedVolume + itemVolume) < parkingSpaceVolume) {
            ITEMS.add(item);
        } else {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        }
    }

    private double getTotalOccupiedVolume() {
        double totalOccupiedVolume = 0;
        if(vehicle != null) {
            if(vehicle.getSize().getType().equals("Volume")) {
                totalOccupiedVolume += ((Volume) vehicle.getSize()).getValue();
            } else {
                totalOccupiedVolume += ((Dimension) vehicle.getSize()).getLength()
                        * ((Dimension) vehicle.getSize()).getWidth()
                        * ((Dimension) vehicle.getSize()).getHeight();
            }
        }
        if(!ITEMS.isEmpty()) {
            for (Item i: ITEMS) {
                if(i.getSize().getType().equals("Volume")) {
                    totalOccupiedVolume += ((Volume) i.getSize()).getValue();
                } else {
                    totalOccupiedVolume += ((Dimension) i.getSize()).getLength()
                            * ((Dimension) i.getSize()).getWidth()
                            * ((Dimension) i.getSize()).getHeight();
                }
            }
        }
        return totalOccupiedVolume;
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
