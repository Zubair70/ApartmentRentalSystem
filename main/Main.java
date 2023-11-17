package main;

import dao.ApartmentDAOImpl;
import dao.ParkingSpaceDAOImpl;
import dao.PersonDAOImpl;
import dao.RoomDAOImpl;
import model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PersonDAOImpl personDAO;
    static ApartmentDAOImpl apartmentDAO;
    static RoomDAOImpl roomDAO;
    static ParkingSpaceDAOImpl parkingSpaceDAO;

    public static void loadData() {
        try {
            personDAO = new PersonDAOImpl();
            apartmentDAO = new ApartmentDAOImpl();
            roomDAO = new RoomDAOImpl();
            parkingSpaceDAO = new ParkingSpaceDAOImpl();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void showStartMenu() {
        int option;
        //Runs until the user select a valid option to login or exit
        while (true) {
            System.out.print("Please select an option from menu:\n" +
                    "1. Login" +
                    "2. Exit" +
                    "Please enter an option: ");
            try {
                option = scanner.nextInt();
                if (option == 1) {
                    break;
                } else if (option == 2) {
                    System.out.println("\n...... Good Bye! ......\n");
                    System.exit(0);
                } else {
                    printErrorMsg("Invalid option entered.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }
    }

    public static void showMainMenu(boolean isAdmin, Person loggedInPerson) {
        int option;
        while (true) {
            if (isAdmin) {
                System.out.print("Please select an option from the menu.\n" +
                        "1. Create Apartment\n" +
                        "2. Create Room\n" +
                        "3. Create Parking Space\n" +
                        "4. Register a Person\n" +
                        "5. List Apartments\n" +
                        "6. List Rooms\n" +
                        "7. List Parking Spaces\n" +
                        "8. View Personal info\n" +
                        "9. Logout\n" +
                        "Please enter an option(number): ");
                try {
                    option = scanner.nextInt();
                    if (option == 1) {
                        createApartment();
                    } else if (option == 2) {
                        createRoom();
                    } else if (option == 3) {
                        createParkingSpace();
                    } else if (option == 4) {
                        registerPerson();
                    } else if (option == 5) {
                        if(loggedInPerson.getRentedSpaces().isEmpty()) {
                            System.out.println("\nNo Apartment rented yet.");
                        } else {
                            loggedInPerson.getRentedSpaces().forEach(apartment -> printApartmentDetails(apartment, ""));
                        }
                    } else if (option == 6) {
                        if(loggedInPerson.getRentedSpaces().isEmpty()) {
                            System.out.println("\nNo Parking Space rented yet.");
                        } else {
                            loggedInPerson.getRentedSpaces().forEach(parkingSpace -> printParkingSpaceDetails(parkingSpace, ""));
                        }
                    } else if (option == 7) {
                        if(loggedInPerson.getRentedSpaces().isEmpty()) {
                            System.out.println("\nNo Room rented yet.");
                        } else {
                            loggedInPerson.getRentedSpaces().forEach(parkingSpace -> printParkingSpaceDetails(parkingSpace, ""));
                        }
                    } else if (option == 8) {
                        printPersonDetails(loggedInPerson, "");
                    } else if (option == 9) {
                        loggedInPerson = null;
                        return;
                    } else {
                        printErrorMsg("Invalid option entered.");
                    }
                } catch (NumberFormatException e) {
                    printErrorMsg("Invalid input.");
                }
            } else {
                System.out.print("Please select an option from the menu.\n" +
                        "1. Rent an Apartment\n" +
                        "2. Rent a Parking Space\n" +
                        "3. Rent a Room\n" +
                        "4. List Rented Apartments\n" +
                        "5. List Rented Parking Spaces\n" +
                        "6. List Rented Rooms\n" +
                        "7. View personal info\n" +
                        "8. Logout\n" +
                        "Please enter an option(number): ");
            }
        }
    }

    public static void createApartment() {
        int option;
        Area size = null;
        int floor = 0;
        while (true) {
            System.out.print("Select Apartment size type: \n" +
                    "1. Volume\n" +
                    "2. Dimension\n" +
                    "3. Go back" +

                    "Please enter an option(number): ");
            try {
                option = scanner.nextInt();
                if (option == 1) {
                    size = new Volume("Volume", promptVolume());
                    break;
                } else if (option == 2) {
                    double[] dimensions = promptDimension();
                    size = new Dimension("Dimension", dimensions[0], dimensions[1], dimensions[2]);
                    break;
                } else if (option == 3) {
                    return;
                } else {
                    printErrorMsg("Invalid option entered.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        while (true) {
            System.out.print("Enter floor no: ");
            try {
                floor = scanner.nextInt();
                if (floor > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid floor no.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        apartmentDAO.save(new Apartment(size, false, floor, null));
        printInfoMsg("Apartment created");
    }

    public static void createParkingSpace() {
        int option;
        Area size = null;
        while (true) {
            System.out.print("Select Parking Space size type: \n" +
                    "1. Volume\n" +
                    "2. Dimension\n" +
                    "3. Go back" +

                    "Please enter an option(number): ");
            try {
                option = scanner.nextInt();
                if (option == 1) {
                    size = new Volume("Volume", promptVolume());
                    break;
                } else if (option == 2) {
                    double[] dimensions = promptDimension();
                    size = new Dimension("Dimension", dimensions[0], dimensions[1], dimensions[2]);
                    break;
                } else if (option == 3) {
                    return;
                } else {
                    printErrorMsg("Invalid option entered.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        parkingSpaceDAO.save(new ParkingSpace(size, false, null, null));
        printInfoMsg("Parking Space created.");
    }

    public static void createRoom() {
        int option;
        Area size = null;
        int capacity = 0;
        while (true) {
            System.out.print("Select Apartment size type: \n" +
                    "1. Volume\n" +
                    "2. Dimension\n" +
                    "3. Go back" +

                    "Please enter an option(number): ");
            try {
                option = scanner.nextInt();
                if (option == 1) {
                    size = new Volume("Volume", promptVolume());
                    break;
                } else if (option == 2) {
                    double[] dimensions = promptDimension();
                    size = new Dimension("Dimension", dimensions[0], dimensions[1], dimensions[2]);
                    break;
                } else if (option == 3) {
                    return;
                } else {
                    printErrorMsg("Invalid option entered.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        while (true) {
            System.out.print("Enter no. of items room can accommodate: ");
            try {
                capacity = scanner.nextInt();
                if (capacity > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid no.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        roomDAO.save(new Room(size, false, capacity));
        printInfoMsg("Room created.");
    }

    public static double promptVolume() {
        double volume;
        while (true) {
            System.out.print("Enter volume: ");
            try {
                volume = scanner.nextDouble();
                if (volume > 0) {
                    return volume;
                } else {
                    printErrorMsg("Invalid volume.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }
    }

    public static double[] promptDimension() {
        double[] dimensions = new double[3];
        while (true) {
            System.out.print("Enter length: ");
            try {
                dimensions[0] = scanner.nextDouble();
                if (dimensions[0] > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid length.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        while (true) {
            System.out.print("Enter width: ");
            try {
                dimensions[1] = scanner.nextDouble();
                if (dimensions[1] > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid width.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        while (true) {
            System.out.print("Enter height: ");
            try {
                dimensions[2] = scanner.nextDouble();
                if (dimensions[2] > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid height.");
                }
            } catch (NumberFormatException e) {
                printErrorMsg("Invalid input");
            }
        }

        return dimensions;
    }

    public static void registerPerson() {
        String username, password, name, surname, peselNumber, address, dateOfBirth;
        Date dob = null;
        System.out.print("Enter username of the person: ");
        username = scanner.next();
        System.out.print("Enter password of the person: ");
        password = scanner.next();
        System.out.print("Enter name of the person: ");
        name = scanner.nextLine();
        System.out.print("Enter surname of the person: ");
        surname = scanner.next();
        while (true) {
            try {
                System.out.print("Enter pesel no. of the person: ");
                peselNumber = scanner.next();
                if (peselNumber.length() == 9) {
                    Integer.parseInt(peselNumber);
                    break;
                } else {
                    printErrorMsg("Invalid pesel number.");
                }
            } catch (Exception e) {
                printErrorMsg("Invalid input: " + e.getMessage());
            }
        }
        System.out.print("Enter address of the person: ");
        address = scanner.nextLine();
        while (true) {
            try {
                System.out.print("Enter date of birth (dd/mm/yyyy) of the person: ");
                dateOfBirth = scanner.next();
                String[] dateParts = dateOfBirth.split("/");
                if (dateParts.length == 3) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[2]) - 1, Integer.parseInt(dateParts[0]));
                    dob = calendar.getTime();
                    break;
                } else {
                    printErrorMsg("Invalid date.");
                }
            } catch (Exception e) {
                printErrorMsg("Invalid input: " + e.getMessage());
            }
        }
        personDAO.save(new Person(surname, password, name, surname, peselNumber, address, dob, null, null));
        printInfoMsg("Person registered.");
    }

    public static void printPersonDetails(Person loggedInPerson, String whiteSpace) {
        System.out.println(whiteSpace + "Name: " + loggedInPerson.getName() +
                "\n" + whiteSpace + "Username: " + loggedInPerson.getUsername() +
                "\n" + whiteSpace + "Password: " + loggedInPerson.getPassword() +
                "\n" + whiteSpace + "Surname: " + loggedInPerson.getSurname() +
                "\n" + whiteSpace + "Pesel No: " + loggedInPerson.getPeselNumber() +
                "\n" + whiteSpace + "Address: " + loggedInPerson.getAddress() +
                "\n" + whiteSpace + "Date of Birth: " + loggedInPerson.getDateOfBirth().toString() +
                "\n" + whiteSpace + "Rented Apartment -> [");
        if (loggedInPerson.getRentedSpaces().isEmpty()) {
            System.out.println(whiteSpace + "\t" + "No Apartment rented yet.");
        } else {
            loggedInPerson.getRentedSpaces().forEach(space -> printSpaceDetails(space, whiteSpace + "\t"));
        }
        System.out.println("]" +
                "\nRented Parking Spaces -> [");
            loggedInPerson.getRentedParkingSpaces().forEach(parkingSpace -> printSpaceDetails(parkingSpace, whiteSpace + "\t"));
        System.out.println("]" +
                "\nTenant Letters -> [");
            loggedInPerson.getTenantLetters().forEach(tenantLetter -> printTenantLetterDetails(tenantLetter, whiteSpace + "\t"));
        System.out.println("]");
    }

    public static void printSpaceDetails(Space space, String whiteSpace) {
        printAreaDetails(space.getSize(), whiteSpace + "\t");
        if (space instanceof Apartment) {
            printApartmentDetails((Apartment) space, whiteSpace + "\t");
        } else if (space instanceof ParkingSpace) {
            printParkingSpaceDetails((ParkingSpace) space, whiteSpace + "\t");
        } else {
            printRoomDetails((Room) space, whiteSpace + "\t");
        }
    }

    public static void printApartmentDetails(Apartment apartment, String whiteSpace) {
        System.out.println("\n" + whiteSpace + "Floor no.: " + apartment.getFloor() + "\n");
    }

    public static void printParkingSpaceDetails(ParkingSpace parkingSpace, String whiteSpace) {
        System.out.println(whiteSpace + "Vehicle details -> [");
        printVehicleDetails(parkingSpace.getVehicle(), whiteSpace + "\t");
        System.out.println(whiteSpace + "]");
        System.out.println("\n" + whiteSpace + "Rented By: [");
        printPersonDetails(parkingSpace.getRentedBy(), whiteSpace + "\t");
        System.out.println("\n" + whiteSpace + "]\n" +
                whiteSpace + "Items stored -> [");
        parkingSpace.getItems().forEach(item -> printItemDetails(item, whiteSpace + "\t"));
        System.out.println(whiteSpace + "]");
    }

    public static void printRoomDetails(Room room, String whiteSpace) {
        System.out.println(whiteSpace + "Rental persons -> [");
        room.getRentalPersons().forEach(person -> printPersonDetails(person, whiteSpace + "\t"));
        System.out.println(whiteSpace + "]");
    }

    public static void printVehicleDetails(Vehicle vehicle, String whiteSpace) {
        System.out.println(whiteSpace + "Name: " + vehicle.getName() + "\n" +
                whiteSpace + "Vehicle type: " + vehicle.getType());
        printAreaDetails(vehicle.getSize(), whiteSpace + "\t");
        if (vehicle instanceof Car) {
            System.out.println(whiteSpace + "Car -> [");
            printCarDetails((Car) vehicle, whiteSpace + "\t");
            System.out.println(whiteSpace + "]");
        } else if (vehicle instanceof Boat) {
            System.out.println(whiteSpace + "Boat -> [");
            printBoatDetails((Boat) vehicle, whiteSpace + "\t");
            System.out.println(whiteSpace + "]");
        } else {
            System.out.println(whiteSpace + "Motorcycle -> [");
            printMotorcycleDetails((Motorcycle) vehicle, whiteSpace + "\t");
            System.out.println(whiteSpace + "]");
        }
    }

    public static void printItemDetails(Item item, String whiteSpace) {
        System.out.println("\n" + whiteSpace + "Name: " + item.getName() + "\n");
        printAreaDetails(item.getSize(), whiteSpace);
    }

    public static void printTenantLetterDetails(TenantLetter tenantLetter, String whiteSpace) {
        System.out.println("\n" + whiteSpace + "Content: " + tenantLetter.getContent());
    }

    public static void printCarDetails(Car car, String whiteSpace) {
        System.out.println(whiteSpace + "Model: " + car.getModel() +
                "\nSeating Capacity: " + car.getSeatingCapacity() +
                "\nColor: " + car.getColor() +
                "\nGas Type: " + car.getGasType());
    }

    public static void printBoatDetails(Boat boat, String whiteSpace) {
        System.out.println(whiteSpace + "Is Boat with engine: " + (boat.isWithEngine() ? "Yes" : "No"));
    }

    public static void printMotorcycleDetails(Motorcycle motorcycle, String whiteSpace) {
        System.out.println(whiteSpace + "Is Heavy Bike: " + (motorcycle.isHeavyBike() ? "Yes" : "No") +
                "\n" + whiteSpace + "Engine Size: " + motorcycle.getEngineSize());
    }

    public static void printAreaDetails(Area area, String whiteSpace) {
        System.out.println(whiteSpace + "Size: ");
        if (area instanceof Volume) {
            System.out.println(area.getType() + " -> " + ((Volume) area).getValue() + " cubic meters");
        } else {
            System.out.println(area.getType() + " -> " + ((Dimension) area).getLength() + "x" + ((Dimension) area).getWidth() + "x" + ((Dimension) area).getHeight());
        }
    }

    public static void printInfoMsg(String msg) {
        System.out.println("\n------- " + msg + " -------\n");
    }

    public static void printErrorMsg(String msg) {
        System.err.println("\nxxxxxx " + msg + " xxxxxx\n");
    }

    public static void main(String[] args) {
        loadData();
        String username, password;
        Person loggedInPerson = null;
        boolean isAdmin = false;
        int option;
        //Runs until user selects to exit the program
        while (true) {
            System.out.println("*********** Welcome to Apartment Rental System ***********\n");
            showStartMenu();
            username = password = "";
            loggedInPerson = null;
            isAdmin = false;
            //Runs until the user credentials are verified
            while (true) {
                System.out.print("Please enter login credentials:\n" +
                        "Enter username (or B to go back): ");
                username = scanner.next();
                if (username.equalsIgnoreCase("b")) {
                    break;
                } else {
                    System.out.print("\nUsername or password is incorrect, please try again!\n");
                }
                System.out.print("\nEnter password (or B to go back): ");
                password = scanner.next();
                if (password.equalsIgnoreCase("b")) {
                    break;
                } else {
                    System.out.print("\nUsername or password is incorrect, please try again!\n");
                }
                loggedInPerson = personDAO.getPersonByCredentials(username, password);
                if (loggedInPerson != null) {
                    isAdmin = loggedInPerson.getUsername().equals("admin");
                    System.out.print("Welcome: " + loggedInPerson.getUsername() + ", " + loggedInPerson.getName());
                    showMainMenu(isAdmin, loggedInPerson);
                } else {
                    System.out.print("\nUsername or password is incorrect, please try again!\n");
                }
            }
        }
    }

}
