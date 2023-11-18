package model;

/**
 * Tenant letter to be sent to the person
 */
public class TenantLetter {

    /**
     * unique identifier
     */
    private int id;

    /**
     * content of the letter
     */
    private String content;

    /**
     * counter to generate unique id on object creation
     */
    private static int tenantLetterIdCounter = 0;

    public TenantLetter(String content) {
        id = ++tenantLetterIdCounter;
        this.content = content;
    }

    public TenantLetter() {
    }

    //Setters and Getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
