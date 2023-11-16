package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;
import model.types.GasType;
import model.types.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements HandlerDAO<Vehicle>{
    private static final List<Vehicle> VEHICLES = new ArrayList<>();

    public VehicleDAOImpl() throws Exception {
        //loads data in the list only one time
        if(VEHICLES.isEmpty()) {
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.CAR_RECORD) || line.startsWith(IConstants.BOAT_RECORD)
                        || line.startsWith(IConstants.MOTORCYCLE_RECORD)) {
                    String[] lineParts = line.split(IConstants.VALUE_SEPARATOR);
                    String name = lineParts[0];
                    VehicleType type = VehicleType.valueOf(lineParts[1]);
                    Area area = null;
                    if (!lineParts[2].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[2].startsWith(IConstants.VOLUME)) {
                            area = new Volume("Volume", Double.parseDouble(lineParts[2].substring(1)));
                        } else if (lineParts[2].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[2].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                            area = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }

                    Vehicle vehicle = null;
                    if(line.startsWith(IConstants.CAR_RECORD)) {
                        vehicle = new Car("Toyota", type, area, lineParts[3], Integer.parseInt(lineParts[4]), lineParts[5], GasType.valueOf(lineParts[6]));
                    } else if(line.startsWith(IConstants.BOAT_RECORD)) {
                        vehicle = new Boat("", type, area, Boolean.parseBoolean(lineParts[3]));
                    } else if(line.startsWith(IConstants.MOTORCYCLE_RECORD)) {
                        vehicle = new Motorcycle("", type, area, Boolean.parseBoolean(lineParts[3]), lineParts[4]);
                    }

                    VEHICLES.add(vehicle);
                }
            });
        }
    }

    @Override
    public int size() {
        return VEHICLES.size();
    }

    @Override
    public boolean save(Vehicle obj) {
        return VEHICLES.add(obj);
    }

    @Override
    public Vehicle getByIndex(int index) {
        return VEHICLES.get(index);
    }

    @Override
    public Vehicle getById(int id) {
        return VEHICLES.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Vehicle obj) {
        return VEHICLES.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Vehicle obj) {
        return VEHICLES.set(VEHICLES.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return VEHICLES.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return VEHICLES.remove(VEHICLES.indexOf(getById(id))) != null;
    }
}
