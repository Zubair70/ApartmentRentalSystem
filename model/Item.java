package model;

/**
 * represents an item, which can be stored in the parking space
 */
public class Item {

    /**
     * unique identifier
     */
    private int id;

    /**
     * name of the item
     */
    private String name;

    /**
     * size of the item, it occupies
     */
    private Area size;

    private static int itemIdCounter = 0;

    public Item(String name, Area area) {
        id = ++itemIdCounter;
        this.name = name;
        this.size = area;
    }

    public Item() {
    }

    //Setters and Getters
    public void setId(int id) {
        itemIdCounter += id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getSize() {
        return size;
    }

    public void setSize(Area size) {
        this.size = size;
    }
}
