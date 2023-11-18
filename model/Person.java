package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Date of birth of the person
     */
    private Date dateOfBirth;

    /**
     * spaces rented by the person
     */
    private List<Space> RENTED_SPACES;

    /**
     * tenant letters that were sent to the person
     */
    private final List<TenantLetter> TENANT_LETTERS;

    /**
     * counter to generate a unique id when creating a person object
     */
    private static int personIdCounter = 0;

    public Person(String username, String password, String name, String surname,
                  String peselNumber, String address, Date dateOfBirth) {
        id = ++personIdCounter;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.peselNumber = peselNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        RENTED_SPACES = new ArrayList<>();
        TENANT_LETTERS = new ArrayList<>();
    }

    public Person() {
        RENTED_SPACES = new ArrayList<>();
        TENANT_LETTERS = new ArrayList<>();
    }

    //Setters and Getters
    public void setId(int id) {
        personIdCounter += id;
        this.id = id;
    }

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Adds a Space in the list
     *
     * @param space Space object to be added
     */
    public boolean addRentedSpace(Space space) {
        long totalRentedApartments = RENTED_SPACES.stream()
                .filter(rentedSpace -> rentedSpace instanceof Apartment)
                .count();
        long totalRentedParkingSpaces = RENTED_SPACES.stream()
                .filter(rentedSpace -> rentedSpace instanceof ParkingSpace)
                .count();
        if((totalRentedApartments + totalRentedParkingSpaces) == 5) {
            System.out.println("Cannot rent another space, " +
                    "Total Rented Apartments: " + totalRentedApartments +
                    ", Total Rented Parking Spaces: " + totalRentedParkingSpaces);
            return false;
        }
        RENTED_SPACES.add(space);
        return true;
    }

    /**
     * Removes a Space object from list
     *
     * @param space Space object to be removed
     */
    public void removeRentedSpace(Space space) {
        RENTED_SPACES.removeIf(s -> s.getId() == space.getId());
    }

    /**
     * Returns a Space object filtered by index
     *
     * @param index index of the Space object
     * @return object of the Space
     */
    public Space getRentedSpaceByIndex(int index) {
        return RENTED_SPACES.get(index);
    }

    /**
     * Returns a Space object filtered by its unique identifier
     *
     * @param id unique identifier of the Space object
     * @return object of the Space
     */
    public Space getRentedSpaceById(int id) {
        return RENTED_SPACES.stream()
                .filter(letter -> letter.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of rented spaces.<br>
     * Creates new arraylist everytime when called, so that, reference address must not match the original list
     *
     * @return Returns the list of rented spaces of the person.
     */
    public List<Space> getRentedSpaces() {
        return new ArrayList<>(RENTED_SPACES);
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
     * Returns the list of tenantLetters.<br>
     * Creates new arraylist everytime when called, so that, reference address must not match the original list
     *
     * @return Returns the list of tenantLetters of the person.
     */
    public List<TenantLetter> getTenantLetters() {
        return new ArrayList<>(TENANT_LETTERS);
    }
}
