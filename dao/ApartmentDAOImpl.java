package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDAOImpl implements HandlerDAO<Apartment> {
    private static final List<Apartment> APARTMENTS = new ArrayList<>();

    public ApartmentDAOImpl() throws Exception {
        //loads data in the list only one time
        if(APARTMENTS.isEmpty()) {
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.APARTMENT_RECORD)) {
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
                    List<Room> rooms = null;
                    if (!lineParts[3].equals(IConstants.NULL_RECORD)) {
                        String[] roomParts = lineParts[0].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                    }
                    APARTMENTS.add(new Apartment(area, Integer.parseInt(lineParts[1]), Boolean.parseBoolean(lineParts[2])));
                }
            });
        }
    }

    @Override
    public int size() {
        return APARTMENTS.size();
    }

    @Override
    public boolean save(Apartment obj) {
        return APARTMENTS.add(obj);
    }

    @Override
    public Apartment getByIndex(int index) {
        return APARTMENTS.get(index);
    }

    @Override
    public Apartment getById(int id) {
        return APARTMENTS.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Apartment obj) {
        return APARTMENTS.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Apartment obj) {
        return APARTMENTS.set(APARTMENTS.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return APARTMENTS.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return APARTMENTS.remove(APARTMENTS.indexOf(getById(id))) != null;
    }
}
