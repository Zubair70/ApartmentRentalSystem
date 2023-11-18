package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import exception.TooManyThingsException;
import model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SpaceDAOImpl implements HandlerDAO<Space> {
    private static final List<Space> SPACES = new ArrayList<>();
    private PersonDAOImpl personDAO;
    private ItemDAOImpl itemDAO;
    private VehicleDAOImpl vehicleDAO;

    @Override
    public void loadData() throws Exception {
        //loads data in the list only one time
        if (SPACES.isEmpty()) {
            itemDAO = new ItemDAOImpl();
            vehicleDAO = new VehicleDAOImpl();
            personDAO = new PersonDAOImpl();

            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.APARTMENT_RECORD)
                        || line.startsWith(IConstants.PARKING_SPACE_RECORD)
                        || line.startsWith(IConstants.ROOM_RECORD)) {
                    String[] lineParts = line.substring(line.indexOf(":") + 1).split(IConstants.VALUE_SEPARATOR);
                    Area area = null;
                    if (!lineParts[1].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[1].startsWith(IConstants.VOLUME)) {
                            area = new Volume("Volume", Double.parseDouble(lineParts[1].replace(IConstants.VOLUME, "")));
                        } else if (lineParts[1].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[1].replace(IConstants.DIMENSION, "").split(IConstants.SUB_VALUE_SEPARATOR);
                            area = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }

                    Date startDate = null;
                    Calendar calendar;
                    String[] dateParts;
                    if(!lineParts[3].equals(IConstants.NULL_RECORD)) {
                        dateParts = lineParts[3].split(IConstants.DATE_SEPARATOR); // dd/mm/yyyy
                        calendar = Calendar.getInstance();
                        calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                        startDate = calendar.getTime();
                    }

                    Date endDate = null;
                    if(!lineParts[4].equals(IConstants.NULL_RECORD)) {
                        dateParts = lineParts[4].split(IConstants.DATE_SEPARATOR); // dd/mm/yyyy
                        calendar = Calendar.getInstance();
                        calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                        endDate = calendar.getTime();
                    }

                    Space space = null;
                    if (line.startsWith(IConstants.APARTMENT_RECORD)) {
                        Person rentedBy = null;
                        if(!lineParts[6].equalsIgnoreCase(IConstants.NULL_RECORD)) {
                            rentedBy = personDAO.getById(Integer.parseInt(lineParts[6]));
                        }
                        Apartment apartment = new Apartment();
                        apartment.setId(Integer.parseInt(lineParts[0]));
                        apartment.setSize(area);
                        apartment.setRented(Boolean.parseBoolean(lineParts[2]));
                        apartment.setStartDate(startDate);
                        apartment.setEndDate(endDate);
                        apartment.setFloor(Integer.parseInt(lineParts[5]));
                        apartment.setRentedBy(rentedBy);
                        space = apartment;
                    } else if (line.startsWith(IConstants.PARKING_SPACE_RECORD)) {
                        Vehicle vehicle = null;
                        if (!lineParts[5].equals(IConstants.NULL_RECORD)) {
                            vehicle = vehicleDAO.getById(Integer.parseInt(lineParts[5]));
                        }
                        Person rentedBy = null;
                        if(!lineParts[6].equalsIgnoreCase(IConstants.NULL_RECORD)) {
                            rentedBy = personDAO.getById(Integer.parseInt(lineParts[6]));
                        }
                        ParkingSpace parkingSpace = new ParkingSpace();
                        parkingSpace.setId(Integer.parseInt(lineParts[0]));
                        parkingSpace.setSize(area);
                        parkingSpace.setRented(Boolean.parseBoolean(lineParts[2]));
                        parkingSpace.setStartDate(startDate);
                        parkingSpace.setEndDate(endDate);
                        parkingSpace.setVehicle(vehicle);
                        parkingSpace.setRentedBy(rentedBy);

                        if (!lineParts[7].equals(IConstants.NULL_RECORD)) {
                            String[] recordParts = lineParts[7].replace(IConstants.ITEM_RECORD, "").split(IConstants.SUB_VALUE_SEPARATOR);
                            for(String rec : recordParts) {
                                try {
                                    parkingSpace.addItem(itemDAO.getById(Integer.parseInt(rec)));
                                } catch (TooManyThingsException ex) {
                                    System.err.println(ex.getClass().getSimpleName() + ": " + ex.getMessage() + ", id: " + rec);
                                }
                            }
                        }
                        space = parkingSpace;
                    } else if (line.startsWith(IConstants.ROOM_RECORD)) {
                        Room newRoom = new Room();
                        newRoom.setId(Integer.parseInt(lineParts[0]));
                        newRoom.setSize(area);
                        newRoom.setRented(Boolean.parseBoolean(lineParts[2]));
                        newRoom.setStartDate(startDate);
                        newRoom.setEndDate(endDate);
                        newRoom.setCapacity(Integer.parseInt(lineParts[5]));
                        if (!lineParts[6].equals(IConstants.NULL_RECORD)) {
                            String[] recordParts = lineParts[6].replace(IConstants.PERSON_RECORD, "").split(IConstants.SUB_VALUE_SEPARATOR);
                            for(String rec : recordParts) {
                                newRoom.addRentalPerson(personDAO.getById(Integer.parseInt(rec)));
                            }
                        }
                        space = newRoom;
                    }
                    SPACES.add(space);
                }
            });
        }
    }

    @Override
    public int size() {
        return SPACES.size();
    }

    public List<Space> getApartments() {
        return SPACES.stream().filter(space -> space instanceof Apartment).collect(Collectors.toList());
    }

    public List<Space> getEmptyApartments() {
        return SPACES.stream().filter(space -> space instanceof Apartment && !space.isRented()).collect(Collectors.toList());
    }

    public List<Space> getParkingSpaces() {
        return SPACES.stream().filter(space -> space instanceof ParkingSpace).collect(Collectors.toList());
    }

    public List<Space> getEmptyParkingSpaces() {
        return SPACES.stream().filter(space -> space instanceof ParkingSpace && !space.isRented()).collect(Collectors.toList());
    }

    public List<Space> getRooms() {
        return SPACES.stream().filter(space -> space instanceof Room).collect(Collectors.toList());
    }

    public List<Space> getEmptyRooms() {
        return SPACES.stream().filter(space -> space instanceof Room && !space.isRented()).collect(Collectors.toList());
    }

    @Override
    public boolean save(Space obj) {
        return SPACES.add(obj);
    }

    @Override
    public Space getByIndex(int index) {
        return SPACES.get(index);
    }

    @Override
    public Space getById(int id) {
        return SPACES.stream()
                .filter(space -> space.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Space obj) {
        return SPACES.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Space obj) {
        return SPACES.set(SPACES.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return SPACES.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return SPACES.remove(SPACES.indexOf(getById(id))) != null;
    }

    @Override
    public void updateData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Space space;
        Calendar calendar;
        boolean isVolume;
        for (int i = 0; i < SPACES.size(); i++) {
            space = SPACES.get(i);
            if(space instanceof Apartment) {
                stringBuilder.append(IConstants.APARTMENT_RECORD);
            } else if(space instanceof ParkingSpace) {
                stringBuilder.append(IConstants.PARKING_SPACE_RECORD);
            } else {
                stringBuilder.append(IConstants.ROOM_RECORD);
            }
            stringBuilder.append(space.getId());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            isVolume = space.getSize().getType().equalsIgnoreCase("volume");
            if(isVolume) {
                stringBuilder.append(IConstants.VOLUME);
                stringBuilder.append(((Volume) space.getSize()).getValue());
            } else {
                stringBuilder.append(IConstants.DIMENSION);
                stringBuilder.append(((Dimension) space.getSize()).getLength());
                stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                stringBuilder.append(((Dimension) space.getSize()).getWidth());
                stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                stringBuilder.append(((Dimension) space.getSize()).getHeight());
            }
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(space.isRented());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            if(space.getStartDate() == null) {
                stringBuilder.append(IConstants.NULL_RECORD);
            } else {
                calendar = Calendar.getInstance();
                calendar.setTime(space.getStartDate());
                stringBuilder.append(calendar.get(Calendar.DATE));
                stringBuilder.append(IConstants.DATE_SEPARATOR);
                stringBuilder.append(calendar.get(Calendar.MONTH) + 1);
                stringBuilder.append(IConstants.DATE_SEPARATOR);
                stringBuilder.append(calendar.get(Calendar.YEAR));
            }
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            if(space.getEndDate() == null) {
                stringBuilder.append(IConstants.NULL_RECORD);
            } else {
                calendar = Calendar.getInstance();
                calendar.setTime(space.getEndDate());
                stringBuilder.append(calendar.get(Calendar.DATE));
                stringBuilder.append(IConstants.DATE_SEPARATOR);
                stringBuilder.append(calendar.get(Calendar.MONTH) + 1);
                stringBuilder.append(IConstants.DATE_SEPARATOR);
                stringBuilder.append(calendar.get(Calendar.YEAR));
            }
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            if(space instanceof Apartment) {
                stringBuilder.append(((Apartment) space).getFloor());
                stringBuilder.append(IConstants.VALUE_SEPARATOR);
                if(((Apartment) space).getRentedBy() != null) {
                    stringBuilder.append(((Apartment) space).getRentedBy().getId());
                } else {
                    stringBuilder.append(IConstants.NULL_RECORD);
                }
            } else if(space instanceof ParkingSpace) {
                if(((ParkingSpace) space).getVehicle() != null) {
                    stringBuilder.append(((ParkingSpace) space).getVehicle().getId());
                    stringBuilder.append(IConstants.VALUE_SEPARATOR);
                } else {
                    stringBuilder.append(IConstants.NULL_RECORD);
                    stringBuilder.append(IConstants.VALUE_SEPARATOR);
                }
                if(((ParkingSpace) space).getRentedBy() != null) {
                    stringBuilder.append(((ParkingSpace) space).getRentedBy().getId());
                } else {
                    stringBuilder.append(IConstants.NULL_RECORD);
                }

                stringBuilder.append(IConstants.VALUE_SEPARATOR);

                if(((ParkingSpace) space).getItems().isEmpty()) {
                    stringBuilder.append(IConstants.NULL_RECORD);
                } else {
                    stringBuilder.append(IConstants.ITEM_RECORD);
                    List<Item> items = ((ParkingSpace) space).getItems();
                    for (int j = 0; j < items.size(); j++) {
                        stringBuilder.append(items.get(j).getId());
                        if(j < items.size() - 1) {
                            stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                        }
                    }
                }
            } else {
                stringBuilder.append(((Room) space).getCapacity());
                stringBuilder.append(IConstants.VALUE_SEPARATOR);
                if(((Room) space).getRentalPersons().isEmpty()) {
                    stringBuilder.append(IConstants.NULL_RECORD);
                } else {
                    stringBuilder.append(IConstants.PERSON_RECORD);
                    List<Person> rentalPersons = ((Room) space).getRentalPersons();
                    for (int j = 0; j < rentalPersons.size(); j++) {
                        stringBuilder.append(rentalPersons.get(j).getId());
                        if(j < rentalPersons.size() - 1) {
                            stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                        }
                    }
                }
            }
            if(i < SPACES.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        FileHandler.updateFile(stringBuilder.toString());
    }
}
