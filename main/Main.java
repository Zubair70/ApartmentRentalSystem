package main;

import dao.ApartmentDAOImpl;
import dao.ParkingSpaceDAOImpl;
import dao.PersonDAOImpl;
import dao.RoomDAOImpl;
import model.Person;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonDAOImpl personDAO = null;
        ApartmentDAOImpl apartmentDAO = null;
        RoomDAOImpl roomDAO = null;
        ParkingSpaceDAOImpl parkingSpaceDAO = null;
        try {
            personDAO = new PersonDAOImpl();
            apartmentDAO = new ApartmentDAOImpl();
            roomDAO = new RoomDAOImpl();
            parkingSpaceDAO = new ParkingSpaceDAOImpl();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("*********** Welcome to Apartment Rental System ***********");
        String username, password;
        Person person = null;
        boolean isAdmin = false;
        while(true) {
            System.out.print("Please enter login credentials:\n Enter username: ");
            username = scanner.next();
            password = scanner.next();
            person = personDAO.getPersonByCredentials(username, password);
            if(person != null) {
                isAdmin = person.getUsername().equals("admin");
                System.out.print("Welcome: " + person.getUsername() + ", " + person.getName());
                break;
            } else {
                System.out.print("\nUsername or password is incorrect, please try again!\n");
            }
        }

        while(true) {
            if(isAdmin) {
                System.out.print("Please an option from the menu.\n" +
                        "1. ");
            } else {
                System.out.print("Please an option from the menu.\n" +
                        "1. ");
            }
        }
    }
}
