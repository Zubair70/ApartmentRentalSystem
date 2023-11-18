package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonDAOImpl implements HandlerDAO<Person> {
    private static final List<Person> PERSONS = new ArrayList<>();
    private SpaceDAOImpl spaceDAO;

    @Override
    public void loadData() throws Exception {
        //loads data in the list only one time
        if (PERSONS.isEmpty()) {
            spaceDAO = new SpaceDAOImpl();

            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.PERSON_RECORD)) {
                    String[] lineParts = line.replace(IConstants.PERSON_RECORD, "").split(IConstants.VALUE_SEPARATOR);

                    Person newPerson = new Person();
                    newPerson.setId(Integer.parseInt(lineParts[0]));
                    newPerson.setUsername(lineParts[1]);
                    newPerson.setPassword(lineParts[2]);
                    newPerson.setName(lineParts[3]);
                    newPerson.setSurname(lineParts[4]);
                    newPerson.setPeselNumber(lineParts[5]);
                    newPerson.setAddress(lineParts[6]);

                    String[] dateParts = lineParts[7].split(IConstants.DATE_SEPARATOR); // dd/mm/yyyy
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));
                    Date dateOfBirth = calendar.getTime();

                    newPerson.setDateOfBirth(dateOfBirth);

                    if (!lineParts[8].equals(IConstants.NULL_RECORD)) {
                        String[] recordParts = lineParts[8].split(IConstants.SUB_VALUE_SEPARATOR);
                        for(String rec : recordParts) {
                            newPerson.addRentedSpace(spaceDAO.getById(Integer.parseInt(rec)));
                        }
                    }

                    if (!lineParts[9].equals(IConstants.NULL_RECORD)) {
                        String[] recordParts = lineParts[9].replace(IConstants.TENANT_LETTER_RECORD, "").split(IConstants.SUB_VALUE_SEPARATOR);
                        for(String rec : recordParts) {
                            newPerson.addTenantLetter(new TenantLetter(rec));
                        }
                    }
                    PERSONS.add(newPerson);
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

    public void updateData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Person person;
        Calendar calendar;
        for (int i = 0; i < PERSONS.size(); i++) {
            person = PERSONS.get(i);
            stringBuilder.append(IConstants.PERSON_RECORD);
            stringBuilder.append(person.getId());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getUsername());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getPassword());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getName());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getSurname());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getPeselNumber());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(person.getAddress());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            calendar = Calendar.getInstance();
            calendar.setTime(person.getDateOfBirth());
            stringBuilder.append(calendar.get(Calendar.DATE));
            stringBuilder.append(IConstants.DATE_SEPARATOR);
            stringBuilder.append(calendar.get(Calendar.MONTH) + 1);
            stringBuilder.append(IConstants.DATE_SEPARATOR);
            stringBuilder.append(calendar.get(Calendar.YEAR));
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            if(person.getRentedSpaces().isEmpty()) {
                stringBuilder.append(IConstants.NULL_RECORD);
            } else {
                List<Space> rentedSpaces= person.getRentedSpaces();
                for (int j = 0; j < rentedSpaces.size(); j++) {
                    stringBuilder.append(rentedSpaces.get(j).getId());
                    if(j < rentedSpaces.size() - 1) {
                        stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                    }
                }
            }
            stringBuilder.append(IConstants.VALUE_SEPARATOR);

            if(person.getTenantLetters().isEmpty()) {
                stringBuilder.append(IConstants.NULL_RECORD);
            } else {
                List<TenantLetter> tenantLetters = person.getTenantLetters();
                for (int j = 0; j < tenantLetters.size(); j++) {
                    stringBuilder.append(tenantLetters.get(j).getId());
                    if(j < tenantLetters.size() - 1) {
                        stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                    }
                }
            }

            if(i < PERSONS.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        FileHandler.updateFile(stringBuilder.toString());
    }
}
