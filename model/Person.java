package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates objects of person
 */
public class Person {

    /**
     * unique identifier of person
     */
    private int id;

    /**
     * first name of the person
     */
    private String firstName;

    /**
     * last name of the person
     */
    private String lastName;

    /**
     * phone number of the person
     */
    private String phoneNumber;

    /**
     * tenant letters that were sent to the person
     */
    private final List<TenantLetter> TENANT_LETTERS;

    /**
     * counter to generate a unique id when creating a person object
     */
    private static int personIdCounter = 0;

    public Person(String firstName, String lastName, String phoneNumber) {
        id = ++personIdCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        TENANT_LETTERS = new ArrayList<>();
    }

    public Person() {
        TENANT_LETTERS = new ArrayList<>();
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Adds a tenantLetter in the apartment
     * @param tenantLetter TenantLetter object to be added
     */
    public void addTenantLetter(TenantLetter tenantLetter) {
        TENANT_LETTERS.add(tenantLetter);
    }

    /**
     * Removes a tenantLetter object from apartment
     * @param tenantLetter TenantLetter object to be removed
     */
    public void removeTenantLetter(TenantLetter tenantLetter) {
        TENANT_LETTERS.removeIf(letter -> letter.getId() == tenantLetter.getId());
    }

    /**
     * Returns a tenantLetter object filtered by index
     * @param index index of the TenantLetter object
     * @return object of the TenantLetter
     */
    public TenantLetter getTenantLetterByIndex(int index) {
        return TENANT_LETTERS.get(index);
    }

    /**
     * Returns a TenantLetter object filtered by its unique identifier
     * @param id unique identifier of the TenantLetter object
     * @return object of the TenantLetter
     */
    public TenantLetter getTenantLetterById(int id) {
        return TENANT_LETTERS.stream()
                .filter(letter -> letter.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of tenantLetters in apartment.<br>
     * Creates new arraylist everytime when called, so that, reference address must not match the original list
     * @return Returns the list of tenantLetters in apartment.
     */
    public List<TenantLetter> getTENANT_LETTERS() {
        return new ArrayList<>(TENANT_LETTERS);
    }
}
