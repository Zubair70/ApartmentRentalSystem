package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceDAOImpl implements HandlerDAO<ParkingSpace> {
    private static final List<ParkingSpace> PARKING_SPACES = new ArrayList<>();
    private VehicleDAOImpl vehicleDAO;

    public ParkingSpaceDAOImpl() throws Exception {
        if(PARKING_SPACES.isEmpty()) {
            vehicleDAO = new VehicleDAOImpl();
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if(line.startsWith(IConstants.PARKING_SPACE_RECORD)) {
                    String[] lineParts = line.split(IConstants.VALUE_SEPARATOR);
                    Area size = null;
                    if (!lineParts[0].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[0].startsWith(IConstants.VOLUME)) {
                            size = new Volume("Volume", Double.parseDouble(lineParts[0].substring(1)));
                        } else if (lineParts[0].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[0].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                            size = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }

                    Vehicle vehicle = null;
                    if (!lineParts[1].equals(IConstants.NULL_RECORD)) {
                        vehicle = vehicleDAO.getById(Integer.parseInt(lineParts[1]));
                    }
                    PARKING_SPACES.add(new ParkingSpace(size, vehicle, Boolean.parseBoolean(lineParts[2])));
                }
            });
        }
    }

    @Override
    public int size() {
        return PARKING_SPACES.size();
    }

    @Override
    public boolean save(ParkingSpace obj) {
        return PARKING_SPACES.add(obj);
    }

    @Override
    public ParkingSpace getByIndex(int index) {
        return PARKING_SPACES.get(index);
    }

    @Override
    public ParkingSpace getById(int id) {
        return PARKING_SPACES.stream()
                .filter(parkingSpace -> parkingSpace.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, ParkingSpace obj) {
        return PARKING_SPACES.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, ParkingSpace obj) {
        return PARKING_SPACES.set(PARKING_SPACES.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return PARKING_SPACES.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return PARKING_SPACES.remove(PARKING_SPACES.indexOf(getById(id))) != null;
    }
}
