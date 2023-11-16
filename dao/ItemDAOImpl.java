package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.Item;
import model.Area;
import model.Volume;
import model.Dimension;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements HandlerDAO<Item>{
    private static final List<Item> ITEMS = new ArrayList<>();

    public ItemDAOImpl() throws Exception {
        //loads data in the list only one time
        if(ITEMS.isEmpty()) {
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.CAR_RECORD) || line.startsWith(IConstants.BOAT_RECORD)
                        || line.startsWith(IConstants.MOTORCYCLE_RECORD)) {
                    String[] lineParts = line.split(IConstants.VALUE_SEPARATOR);
                    Area size = null;
                    if (!lineParts[2].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[2].startsWith(IConstants.VOLUME)) {
                            size = new Volume("Volume", Double.parseDouble(lineParts[2].substring(1)));
                        } else if (lineParts[2].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[2].substring(1).split(IConstants.SUB_VALUE_SEPARATOR);
                            size = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }
                    ITEMS.add(new Item(lineParts[0], size));
                }
            });
        }
    }

    @Override
    public int size() {
        return ITEMS.size();
    }

    @Override
    public boolean save(Item obj) {
        return ITEMS.add(obj);
    }

    @Override
    public Item getByIndex(int index) {
        return ITEMS.get(index);
    }

    @Override
    public Item getById(int id) {
        return ITEMS.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, Item obj) {
        return ITEMS.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, Item obj) {
        return ITEMS.set(ITEMS.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return ITEMS.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return ITEMS.remove(ITEMS.indexOf(getById(id))) != null;
    }
}
