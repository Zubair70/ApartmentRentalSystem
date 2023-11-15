package model;

import java.util.ArrayList;
import java.util.Date;
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
     * username of the person for login
     */
    private String username;

    /**
     * password of the person for login
     */
    private String password;

    /**
     * name of the person
     */
    private String name;

    /**
     * surname of the person
     */
    private String surname;

    /**
     * PESEL number of the person
     */
    private String peselNumber;

    /**
     * address of the person
     */
    private String address;

    private Date dateOfBirth;

    private Apartment rentedApartment;

    private ParkingSpace rentedParkingSpace;

    /**
     * tenant letters that were sent to the person
     */
    private final List<TenantLetter> TENANT_LETTERS;

    /**
     * counter to generate a unique id when creating a person object
     */
    private static int personIdCounter = 0;

    public Person(String username, String password, String name, String surname, String peselNumber, String address) {
        id = ++personIdCounter;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.peselNumber = peselNumber;
        this.address = address;
        TENANT_LETTERS = new ArrayList<>();
    }

    public Person() {
        TENANT_LETTERS = new ArrayList<>();
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Adds a tenantLetter in the list
     *
     * @param tenantLetter TenantLetter object to be added
     */
    public void addTenantLetter(TenantLetter tenantLetter) {
        TENANT_LETTERS.add(tenantLetter);
    }

    /**
     * Removes a tenantLetter object from list
     *
     * @param tenantLetter TenantLetter object to be removed
     */
    public void removeTenantLetter(TenantLetter tenantLetter) {
        TENANT_LETTERS.removeIf(letter -> letter.getId() == tenantLetter.getId());
    }

    /**
     * Returns a tenantLetter object filtered by index
     *
     * @param index index of the TenantLetter object
     * @return object of the TenantLetter
     */
    public TenantLetter getTenantLetterByIndex(int index) {
        return TENANT_LETTERS.get(index);
    }

    /**
     * Returns a TenantLetter object filtered by its unique identifier
     *
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
     *
     * @return Returns the list of tenantLetters of the person.
     */
    public List<TenantLetter> getTENANT_LETTERS() {
        return new ArrayList<>(TENANT_LETTERS);
    }
}
