package model;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<TenantLetter> tenantLetters;

    private static int personIdCounter = 0;

    public Person(String firstName, String lastName, String phoneNumber) {
        id = ++personIdCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        tenantLetters = new ArrayList<>();
    }

    public Person() {
        tenantLetters = new ArrayList<>();
    }

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

    public void addTenantLetter(TenantLetter tenantLetter) {
        tenantLetters.add(tenantLetter);
    }

    public void removeTenantLetter(TenantLetter tenantLetter) {
        tenantLetters.removeIf(letter -> letter.getId() == tenantLetter.getId());
    }

    public TenantLetter getTenantLetterByIndex(int index) {
        return tenantLetters.get(index);
    }

    public TenantLetter getTenantLetterById(int id) {
        return tenantLetters.stream()
                .filter(letter -> letter.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<TenantLetter> getTenantLetters() {
        return new ArrayList<>(tenantLetters);
    }
}
