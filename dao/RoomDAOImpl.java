package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements HandlerDAO<Room>{
    private static final List<Room> ROOMS = new ArrayList<>();
    private PersonDAOImpl personDAO;

    public RoomDAOImpl() throws Exception {
        //loads data in the list only one time
        if(ROOMS.isEmpty()) {
            personDAO = new PersonDAOImpl();
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.ROOM_RECORD)) {
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
                    Room newRoom = new Room(area, Boolean.parseBoolean(lineParts[2]), Integer.parseInt(lineParts[1]));
                    if (!lineParts[3].equals(IConstants.NULL_RECORD)) {
                        String[] recordParts = lineParts[0].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                        for(String rec : recordParts) {
                            newRoom.addRentalPerson(personDAO.getById(Integer.parseInt(rec)));
                        }
                    }
                    ROOMS.add(newRoom);
                }
            });
        }
    }

    @Override
    public int size() {
        return ROOMS.size();
    }

    @Override
    public boolean save(Room obj) {
        return ROOMS.add(obj);
    }

    @Override
    public Room getByIndex(int index) {
        return ROOMS.get(index);
    }

    @Override
    public Room getById(int id) {
        return ROOMS.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Room obj) {
        return ROOMS.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Room obj) {
        return ROOMS.set(ROOMS.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return ROOMS.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return ROOMS.remove(ROOMS.indexOf(getById(id))) != null;
    }
}
