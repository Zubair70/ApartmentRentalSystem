package main;

import dao.ItemDAOImpl;
import dao.PersonDAOImpl;
import dao.SpaceDAOImpl;
import dao.VehicleDAOImpl;
import dao.file_handler.FileHandler;
import model.*;
import model.types.GasType;
import model.types.VehicleType;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PersonDAOImpl personDAO;
    static SpaceDAOImpl spaceDAO;
    static VehicleDAOImpl vehicleDAO;
    static ItemDAOImpl itemDAO;

    public static void loadData() {
        try {
            itemDAO = new ItemDAOImpl();
            itemDAO.loadData();
            vehicleDAO = new VehicleDAOImpl();
            vehicleDAO.loadData();
            spaceDAO = new SpaceDAOImpl();
            spaceDAO.loadData();
            personDAO = new PersonDAOImpl();
            personDAO.loadData();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void showStartMenu() {
        int option;
        //Runs until the user select a valid option to login or exit
        while (true) {
            System.out.print("\nPlease select an option from menu:\n" +
                    "1. Login\n" +
                    "2. Exit\n" +
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
            } catch (InputMismatchException e) {
                printErrorMsg("Invalid input");
                scanner.nextLine();
            }
        }
    }

    public static void showMainMenu(boolean isAdmin, Person loggedInPerson) {
        int option;
        while (true) {
            if (isAdmin) {
                System.out.print("\nPlease select an option from the menu.\n" +
                        "1. Create Apartment\n" +
                        "2. Create Room\n" +
                        "3. Create Parking Space\n" +
                        "4. Register a Person\n" +
                        "5. List Apartments\n" +
                        "6. List Parking Spaces\n" +
                        "7. List Rooms\n" +
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
                        List<Space> spaces = spaceDAO.getApartments();
                        if (spaces.isEmpty()) {
                            System.out.println("\nNo Apartment rented yet.");
                        } else {
                            System.out.println("\nApartments -> [");
                            spaces.forEach(space -> printSpaceDetails(space, ""));
                            System.out.println("]");
                        }
                    } else if (option == 6) {
                        List<Space> spaces = spaceDAO.getParkingSpaces();
                        if (spaces.isEmpty()) {
                            System.out.println("\nNo Parking Space rented yet.");
                        } else {
                            System.out.println("\nParking Spaces -> [");
                            spaces.forEach(space -> printSpaceDetails(space, ""));
                            System.out.println("]");
                        }
                    } else if (option == 7) {
                        List<Space> spaces = spaceDAO.getRooms();
                        if (spaces.isEmpty()) {
                            System.out.println("\nNo Room rented yet.");
                        } else {
                            System.out.println("\nRooms -> [");
                            spaces.forEach(space -> printSpaceDetails(space, ""));
                            System.out.println("]");
                        }
                    } else if (option == 8) {
                        printPersonDetails(loggedInPerson, "");
                    } else if (option == 9) {
                        updateData();
                        loggedInPerson = null;
                        return;
                    } else {
                        printErrorMsg("Invalid option entered.");
                    }
                } catch (InputMismatchException e) {
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
                try {
                    option = scanner.nextInt();
                    if (option == 1) {
                        rentApartment(loggedInPerson);
                    } else if (option == 2) {
                        rentParkingSpace(loggedInPerson);
                    } else if (option == 3) {
                        rentRoom(loggedInPerson);
                    } else if (option == 4) {
                        long apartmentCount = loggedInPerson.getRentedSpaces().stream().filter(space -> space instanceof Apartment).count();
                        if (apartmentCount == 0) {
                            System.out.println("\nNo Apartment rented yet.");
                        } else {
                            System.out.println("\nRented Apartments -> [");
                            loggedInPerson.getRentedSpaces().forEach(space -> {
                                if (space instanceof Apartment) {
                                    printApartmentDetails((Apartment) space, "");
                                }
                            });
                            System.out.println("]");
                        }
                    } else if (option == 5) {
                        long parkingSpaceCount = loggedInPerson.getRentedSpaces().stream().filter(space -> space instanceof ParkingSpace).count();
                        if (parkingSpaceCount == 0) {
                            System.out.println("\nNo Parking Space rented yet.");
                        } else {
                            System.out.println("\nRented Parking Spaces -> [");
                            loggedInPerson.getRentedSpaces().forEach(space -> {
                                if (space instanceof ParkingSpace) {
                                    printParkingSpaceDetails((ParkingSpace) space, "");
                                }
                            });
                            System.out.println("]");
                        }
                    } else if (option == 6) {
                        long roomCount = loggedInPerson.getRentedSpaces().stream().filter(space -> space instanceof Room).count();
                        if (roomCount == 0) {
                            System.out.println("\nNo Room rented yet.");
                        } else {
                            System.out.println("\nRented Rooms -> [");
                            loggedInPerson.getRentedSpaces().forEach(space -> {
                                if (space instanceof Room) {
                                    printRoomDetails((Room) space, "");
                                }
                            });
                            System.out.println("\n]");
                        }
                    } else if (option == 7) {
                        printPersonDetails(loggedInPerson, "");
                    } else if (option == 8) {
                        updateData();
                        loggedInPerson = null;
                        return;
                    } else {
                        printErrorMsg("Invalid option entered.");
                    }
                } catch (InputMismatchException e) {
                    printErrorMsg("Invalid input.");
                }
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
                    "3. Go back\n" +
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
            } catch (InputMismatchException e) {
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
            } catch (InputMismatchException e) {
                printErrorMsg("Invalid input");
            }
        }

        spaceDAO.save(new Apartment(size, false, null, null, floor, null));
        printInfoMsg("Apartment created");
    }

    public static void createParkingSpace() {
        int option;
        Area size = null;
        while (true) {
            System.out.print("Select Parking Space size type: \n" +
                    "1. Volume\n" +
                    "2. Dimension\n" +
                    "3. Go back\n" +
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
            } catch (InputMismatchException e) {
                printErrorMsg("Invalid input");
            }
        }

        spaceDAO.save(new ParkingSpace(size, false, null, null, null, null));
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
                    "3. Go back\n" +

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
            } catch (InputMismatchException e) {
                printErrorMsg("Invalid input");
            }
        }

        while (true) {
            System.out.print("Enter no. of people this room can accommodate: ");
            try {
                capacity = scanner.nextInt();
                if (capacity > 0) {
                    break;
                } else {
                    printErrorMsg("Invalid no.");
                }
            } catch (InputMismatchException e) {
                printErrorMsg("Invalid input");
            }
        }

        spaceDAO.save(new Room(size, false, null, null, capacity));
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
            } catch (InputMismatchException e) {
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
            } catch (InputMismatchException e) {
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
            } catch (InputMismatchException e) {
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
            } catch (InputMismatchException e) {
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
        scanner.nextLine();
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
        scanner.nextLine();
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
        personDAO.save(new Person(username, password, name, surname, peselNumber, address, dob));
        printInfoMsg("Person registered.");
    }

    public static void printPersonDetails(Person loggedInPerson, String whiteSpace) {
        System.out.println(whiteSpace + "\nName: " + loggedInPerson.getName() +
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
        if(spaceDAO.getParkingSpaces().isEmpty()) {
            System.out.println(whiteSpace + "\t" + "No Parking Space rented yet.");
        } else {
            spaceDAO.getParkingSpaces().forEach(space -> printSpaceDetails(space, whiteSpace));
        }
        System.out.println("]" +
                "\nTenant Letters -> [");
        if(loggedInPerson.getTenantLetters().isEmpty()) {
            System.out.println(whiteSpace + "\t" + "No Tenant Letter added yet.");
        } else {
            loggedInPerson.getTenantLetters().forEach(tenantLetter -> printTenantLetterDetails(tenantLetter, whiteSpace + "\t"));
        }
        System.out.println("]\n");
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
        System.out.println(whiteSpace + "Floor no.: " + apartment.getFloor());
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
        if (area instanceof Volume) {
            System.out.println(whiteSpace + area.getType() + " -> " + ((Volume) area).getValue() + " cubic meters");
        } else {
            System.out.println(whiteSpace + area.getType() + " -> " + ((Dimension) area).getLength() + "x" + ((Dimension) area).getWidth() + "x" + ((Dimension) area).getHeight());
        }
    }

    public static void rentApartment(Person loggedInPerson) {
        List<Space> spaces = spaceDAO.getEmptyApartments();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < spaces.size(); i++) {
            Apartment apartment = (Apartment) spaces.get(i);
            stringBuilder.append(i + 1);
            stringBuilder.append(": ");
            stringBuilder.append(apartment.getSize());
            stringBuilder.append("\t\t");
            stringBuilder.append(apartment.getFloor());
            stringBuilder.append("\n");
        }
        int index = -1;
        while (true) {
            System.out.println("Please select an Apartment number: ");
            System.out.println("\nSize\t\tFloor");
            System.out.println(stringBuilder.toString());
            System.out.print("Please enter the number: ");
            try {
                index = scanner.nextInt();
                if ((index - 1) >= 0 && (index - 1) <= spaces.size() - 1) {
                    break;
                } else {
                    printErrorMsg("Invalid number");
                }
            } catch (InputMismatchException ex) {
                printErrorMsg("Invalid input");
            }
        }
        Space space = spaces.get(index);
        space.setRented(true);
        ((Apartment) space).setRentedBy(loggedInPerson);

        Date startDate = null;
        while (true) {
            System.out.print("Enter lease start date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                startDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }

        Date endDate = null;
        while (true) {
            System.out.print("Enter lease end date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                endDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        ((Apartment) space).setStartDate(startDate);
        ((Apartment) space).setEndDate(endDate);
        spaceDAO.updateById(space.getId(), space);
        boolean check = loggedInPerson.addRentedSpace(space);
        if (check) {
            personDAO.updateById(loggedInPerson.getId(), loggedInPerson);
            printInfoMsg("Apartment rented successfully.");
        }
    }

    public static void rentParkingSpace(Person loggedInPerson) {
        List<Space> spaces = spaceDAO.getEmptyParkingSpaces();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < spaces.size(); i++) {
            ParkingSpace parkingSpace = (ParkingSpace) spaces.get(i);
            stringBuilder.append(i + 1);
            stringBuilder.append(": ");
            stringBuilder.append(parkingSpace.getSize());
            stringBuilder.append("\n");
        }
        int index = -1;
        while (true) {
            System.out.println("Please select a Parking Space number: ");
            System.out.println("\nSize");
            System.out.println(stringBuilder.toString());
            System.out.print("Please enter a number: ");
            try {
                index = scanner.nextInt();
                if ((index - 1) >= 0 && (index - 1) <= spaces.size() - 1) {
                    break;
                } else {
                    printErrorMsg("Invalid number");
                }
            } catch (InputMismatchException ex) {
                printErrorMsg("Invalid input");
            }
        }
        Space space = spaces.get(index);
        space.setRented(true);
        ((ParkingSpace) space).setRentedBy(loggedInPerson);

        Date startDate = null;
        while (true) {
            System.out.print("Enter lease start date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                startDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }

        Date endDate = null;
        while (true) {
            System.out.print("Enter lease end date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                endDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }

        ((ParkingSpace) space).setStartDate(startDate);
        ((ParkingSpace) space).setEndDate(endDate);

        String choice;
        while (true) {
            System.out.print("Do you want to add vehicle? (Y/N): ");
            choice = scanner.next();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                break;
            } else {
                printErrorMsg("Invalid input");
            }
        }

        StringBuilder strBuilder = new StringBuilder();
        if (choice.equalsIgnoreCase("y")) {
            VehicleType[] vehicleTypes = VehicleType.values();
            for (int i = 0; i < vehicleTypes.length; i++) {
                VehicleType type = vehicleTypes[i];
                strBuilder.append(i + 1);
                strBuilder.append(": ");
                strBuilder.append(type);
                strBuilder.append("\n");
            }

            index = -1;
            while (true) {
                System.out.print("\nPlease select a vehicle type: ");
                System.out.print(strBuilder.toString());
                System.out.print("Please enter a number: ");
                try {
                    index = scanner.nextInt();
                    if ((index - 1) >= 0 && (index - 1) <= vehicleTypes.length - 1) {
                        break;
                    } else {
                        printErrorMsg("Invalid number");
                    }
                } catch (InputMismatchException ex) {
                    printErrorMsg("Invalid input");
                }
            }

            VehicleType vehicleType = vehicleTypes[index];

            System.out.print("Enter vehicle name: ");
            String name = scanner.nextLine();
            Area size = promptSize();

            Vehicle vehicle;
            if (vehicleType.toString().contains("Car")) {
                System.out.print("Please enter model of the car: ");
                String model = scanner.next();

                int seatingCapacity = 0;
                while (true) {
                    System.out.print("Please enter seating capacity: ");
                    try {
                        seatingCapacity = scanner.nextInt();
                        if (seatingCapacity > 0) {
                            break;
                        } else {
                            printErrorMsg("Invalid number");
                        }
                    } catch (InputMismatchException ex) {
                        printErrorMsg("Invalid input");
                    }
                }

                System.out.print("Please enter color of the car: ");
                String color = scanner.next();

                GasType gasType = null;
                int option;
                while (true) {
                    System.out.print("Please a Gas Type number: \n" +
                            "1. Gasoline\n" +
                            "2. Diesel\n" +
                            "Please enter a number: ");
                    try {
                        option = scanner.nextInt();
                        if (option == 1) {
                            gasType = GasType.GASOLINE;
                            break;
                        } else if (option == 2) {
                            gasType = GasType.DIESEL;
                            break;
                        } else {
                            printErrorMsg("Invalid number");
                        }
                    } catch (InputMismatchException ex) {
                        printErrorMsg("Invalid input");
                    }
                }
                vehicle = new Car(name, vehicleType, size, model, seatingCapacity, color, gasType);
            } else if (vehicleType.toString().equals("Boat")) {
                boolean isWithEngine = false;
                while (true) {
                    System.out.print("Is Boat with engine? (Y/N): ");
                    String input = scanner.next();
                    if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                        isWithEngine = input.equalsIgnoreCase("y") ? true : false;
                        break;
                    } else {
                        printErrorMsg("Invalid input");
                    }
                }
                vehicle = new Boat(name, vehicleType, size, isWithEngine);
            } else {
                boolean isHeavyBike = false;
                while (true) {
                    System.out.print("Is heavy Bike? (Y/N): ");
                    String input = scanner.next();
                    if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                        isHeavyBike = input.equalsIgnoreCase("y") ? true : false;
                        break;
                    } else {
                        printErrorMsg("Invalid input");
                    }
                }

                String engineSize = scanner.next();

                vehicle = new Motorcycle(name, vehicleType, size, isHeavyBike, engineSize);
            }
            ((ParkingSpace) space).setVehicle(vehicle);
        }

        while (true) {
            System.out.print("Do you want to add an Item: ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                System.out.print("Enter item name: ");
                String name = scanner.next();
                Area size = promptSize();
            } else if (input.equalsIgnoreCase("n")) {
                break;
            } else {
                printErrorMsg("Invalid input");
            }
        }

        spaceDAO.updateById(space.getId(), space);
        boolean check = loggedInPerson.addRentedSpace(space);
        if (check) {
            personDAO.updateById(loggedInPerson.getId(), loggedInPerson);
            printInfoMsg("Parking Space rented successfully.");
        }
    }

    public static void rentRoom(Person loggedInPerson) {
        List<Space> spaces = spaceDAO.getEmptyRooms();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < spaces.size(); i++) {
            Room room = (Room) spaces.get(i);
            stringBuilder.append(i + 1);
            stringBuilder.append(": ");
            stringBuilder.append(room.getSize());
            stringBuilder.append("\t\t");
            stringBuilder.append(room.getCapacity());
            stringBuilder.append("\n");
        }
        int index = -1;
        while (true) {
            System.out.println("Please select a Room number: ");
            System.out.println("\nSize\t\tCapacity");
            System.out.println(stringBuilder.toString());
            System.out.print("Please enter a number: ");
            try {
                index = scanner.nextInt();
                if ((index - 1) >= 0 && (index - 1) <= spaces.size() - 1) {
                    break;
                } else {
                    printErrorMsg("Invalid number");
                }
            } catch (InputMismatchException ex) {
                printErrorMsg("Invalid input");
            }
        }

        Space space = spaces.get(index);
        space.setRented(true);

        Date startDate = null;
        while (true) {
            System.out.print("Enter lease start date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                startDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }

        Date endDate = null;
        while (true) {
            System.out.print("Enter lease end date (dd/mm/yyyy): ");
            try {
                String date = scanner.next();
                Calendar calendar = Calendar.getInstance();
                String[] dateParts = date.split("/");
                calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                endDate = calendar.getTime();
                break;
            } catch (Exception ex) {
                printErrorMsg(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }

        ((Room) space).setStartDate(startDate);
        ((Room) space).setEndDate(endDate);

        if(((Room) space).addRentalPerson(loggedInPerson)) {
            spaceDAO.updateById(space.getId(), space);
            boolean check = loggedInPerson.addRentedSpace(space);
            if (check) {
                personDAO.updateById(loggedInPerson.getId(), loggedInPerson);
                printInfoMsg("Room rented successfully.");
            }
        }
    }

    public static Area promptSize() {
        Area size = null;
        int option;
        while (true) {
            System.out.print("Please an Area type: \n" +
                    "1. Volume\n" +
                    "2. Dimension\n" +
                    "Please enter a number: ");
            try {
                option = scanner.nextInt();
                if (option == 1) {
                    size = new Volume();
                    while (true) {
                        System.out.print("Enter value of volume: ");
                        try {
                            ((Volume) size).setValue(scanner.nextDouble());
                            if (((Volume) size).getValue() > 0) {
                                break;
                            } else {
                                printErrorMsg("Invalid value");
                            }
                        } catch (InputMismatchException ex) {
                            printErrorMsg("Invalid input");
                        }
                    }
                    break;
                } else if (option == 2) {
                    size = new Dimension();
                    while (true) {
                        System.out.print("Enter length: ");
                        try {
                            ((Dimension) size).setLength(scanner.nextDouble());
                            if (((Dimension) size).getLength() > 0) {
                                break;
                            } else {
                                printErrorMsg("Invalid length");
                            }
                        } catch (InputMismatchException ex) {
                            printErrorMsg("Invalid input");
                        }
                    }

                    while (true) {
                        System.out.print("Enter width: ");
                        try {
                            ((Dimension) size).setWidth(scanner.nextDouble());
                            if (((Dimension) size).getWidth() > 0) {
                                break;
                            } else {
                                printErrorMsg("Invalid width");
                            }
                        } catch (InputMismatchException ex) {
                            printErrorMsg("Invalid input");
                        }
                    }

                    while (true) {
                        System.out.print("Enter height: ");
                        try {
                            ((Dimension) size).setHeight(scanner.nextDouble());
                            if (((Dimension) size).getHeight() > 0) {
                                break;
                            } else {
                                printErrorMsg("Invalid height");
                            }
                        } catch (InputMismatchException ex) {
                            printErrorMsg("Invalid input");
                        }
                    }
                    break;
                } else {
                    printErrorMsg("Invalid number");
                }
            } catch (InputMismatchException ex) {
                printErrorMsg("Invalid input");
            }
        }

        return size;
    }

    public static void updateData() {
        try {
            FileHandler.clearFile();
            itemDAO.updateData();
            vehicleDAO.updateData();
            spaceDAO.updateData();
            personDAO.updateData();
        } catch (Exception e) {
            printErrorMsg(e.getClass().getSimpleName() + ": " + e.getMessage());
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
            System.out.println("\n*********** Welcome to Apartment Rental System ***********");
            showStartMenu();
            username = password = "";
            loggedInPerson = null;
            isAdmin = false;
            //Runs until the user credentials are verified
            while (true) {
                System.out.print("\nPlease enter login credentials:\n" +
                        "Enter username (or B to go back): ");
                username = scanner.next();
                if (username.equalsIgnoreCase("b")) {
                    break;
                }
                System.out.print("\nEnter password (or B to go back): ");
                password = scanner.next();
                if (password.equalsIgnoreCase("b")) {
                    break;
                }
                loggedInPerson = personDAO.getPersonByCredentials(username, password);
                if (loggedInPerson != null) {
                    isAdmin = loggedInPerson.getUsername().equals("admin");
                    System.out.print("\nWelcome: " + loggedInPerson.getUsername() + ", " + loggedInPerson.getName());
                    showMainMenu(isAdmin, loggedInPerson);
                } else {
                    System.out.print("\nUsername or password is incorrect, please try again!\n");
                }
            }
        }
    }

}
