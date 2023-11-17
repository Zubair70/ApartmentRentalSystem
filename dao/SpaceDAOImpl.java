package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class SpaceDAOImpl implements HandlerDAO<Space> {
    private static final List<Space> SPACES = new ArrayList<>();
    private PersonDAOImpl personDAO;

    public SpaceDAOImpl() throws Exception {
        //loads data in the list only one time
        if (SPACES.isEmpty()) {
            personDAO = new PersonDAOImpl();
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.APARTMENT_RECORD)
                        || line.startsWith(IConstants.PARKING_SPACE_RECORD)
                        || line.startsWith(IConstants.ROOM_RECORD)) {
                    String[] lineParts = line.split(IConstants.VALUE_SEPARATOR);
                    Area area = null;
                    if (!lineParts[0].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[0].startsWith(IConstants.VOLUME)) {
                            area = new Volume("Volume", Double.parseDouble(lineParts[0].substring(1)));
                        } else if (lineParts[0].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[0].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                            area = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }
                    Space space = null;
                    if (line.startsWith(IConstants.APARTMENT_RECORD)) {
                        Person rentedBy = null;
                        if(!lineParts[3].equalsIgnoreCase(IConstants.NULL_RECORD)) {
                            rentedBy = personDAO.getById(Integer.parseInt(lineParts[3]));
                        }
                        space = new Apartment(area, Boolean.parseBoolean(lineParts[1]), Integer.parseInt(lineParts[2]), rentedBy);
                    } else if (line.startsWith(IConstants.PARKING_SPACE_RECORD)) {
                        Person rentedBy = null;
                        if(!lineParts[3].equalsIgnoreCase(IConstants.NULL_RECORD)) {
                            rentedBy = personDAO.getById(Integer.parseInt(lineParts[3]));
                        }

                    } else if (line.startsWith(IConstants.ROOM_RECORD)) {

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
                .filter(person -> person.getId() == id)
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
}
