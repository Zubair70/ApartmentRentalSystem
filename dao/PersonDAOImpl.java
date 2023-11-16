package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.Apartment;
import model.ParkingSpace;
import model.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonDAOImpl implements HandlerDAO<Person> {
    private static final List<Person> PERSONS = new ArrayList<>();
    private ApartmentDAOImpl apartmentDAO;
    private ParkingSpaceDAOImpl parkingSpaceDAO;

    public PersonDAOImpl() throws Exception {
        //loads data in the list only one time
        if (PERSONS.isEmpty()) {
            apartmentDAO = new ApartmentDAOImpl();
            parkingSpaceDAO = new ParkingSpaceDAOImpl();

            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.PERSON_RECORD)) {
                    String[] lineParts = line.split(IConstants.VALUE_SEPARATOR);
                    String[] dateParts = lineParts[6].split(IConstants.DATE_SEPARATOR); // dd/mm/yyyy
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                    Date dateOfBirth = calendar.getTime();
                    Apartment apartment = null;
                    if (!lineParts[7].equals(IConstants.NULL_RECORD)) {
                        apartment = apartmentDAO.getById(Integer.parseInt(lineParts[7]));
                    }
                    ParkingSpace parkingSpace = null;
                    if (!lineParts[8].equals(IConstants.NULL_RECORD)) {
                        parkingSpace = parkingSpaceDAO.getById(Integer.parseInt(lineParts[8]));
                    }
                    PERSONS.add(new Person(lineParts[0], lineParts[1], lineParts[2], lineParts[3], lineParts[4], lineParts[5], dateOfBirth, apartment, parkingSpace));
                }
            });
        }
    }

    @Override
    public int size() {
        return PERSONS.size();
    }

    @Override
    public boolean save(Person obj) {
        return PERSONS.add(obj);
    }

    @Override
    public Person getByIndex(int index) {
        return PERSONS.get(index);
    }

    @Override
    public Person getById(int id) {
        return PERSONS.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Person obj) {
        return PERSONS.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Person obj) {
        return PERSONS.set(PERSONS.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return PERSONS.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return PERSONS.remove(PERSONS.indexOf(getById(id))) != null;
    }

    public Person getPersonByCredentials(String username, String password) {
        return PERSONS.stream()
                .filter(person -> person.getUsername().equals(username) && person.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
