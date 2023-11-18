package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ItemDAOImpl implements HandlerDAO<Item>{
    private static final List<Item> ITEMS = new ArrayList<>();

    @Override
    public void loadData() throws Exception {
        //loads data in the list only one time
        if(ITEMS.isEmpty()) {
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.CAR_RECORD) || line.startsWith(IConstants.BOAT_RECORD)
                        || line.startsWith(IConstants.MOTORCYCLE_RECORD)) {
                    String[] lineParts = line.substring(line.indexOf(":") + 1).split(IConstants.VALUE_SEPARATOR);
                    Area size = null;
                    if (!lineParts[2].equals(IConstants.NULL_RECORD)) {
                        if (lineParts[2].startsWith(IConstants.VOLUME)) {
                            size = new Volume("Volume", Double.parseDouble(lineParts[2].replace(IConstants.DIMENSION, "")));
                        } else if (lineParts[2].startsWith(IConstants.DIMENSION)) {
                            String[] areaParts = lineParts[2].replace(IConstants.DIMENSION, "").split(IConstants.SUB_VALUE_SEPARATOR);
                            size = new Dimension("Dimension", Double.parseDouble(areaParts[0]), Double.parseDouble(areaParts[1]), Double.parseDouble(areaParts[2]));
                        }
                    }
                    Item item = new Item();
                    item.setId(Integer.parseInt(lineParts[0]));
                    item.setName(lineParts[1]);
                    item.setSize(size);
                    ITEMS.add(item);
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

    @Override
    public void updateData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Item item;
        boolean isVolume;
        for (int i = 0; i < ITEMS.size(); i++) {
            item = ITEMS.get(i);
            stringBuilder.append(IConstants.ITEM_RECORD);
            stringBuilder.append(item.getId());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(item.getName());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            isVolume = item.getSize().getType().equalsIgnoreCase("volume");
            if(isVolume) {
                stringBuilder.append(IConstants.VOLUME);
                stringBuilder.append(((Volume) item.getSize()).getValue());
            } else {
                stringBuilder.append(IConstants.DIMENSION);
                stringBuilder.append(((Dimension) item.getSize()).getLength());
                stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                stringBuilder.append(((Dimension) item.getSize()).getWidth());
                stringBuilder.append(IConstants.SUB_VALUE_SEPARATOR);
                stringBuilder.append(((Dimension) item.getSize()).getHeight());
            }
            if(i < ITEMS.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        FileHandler.updateFile(stringBuilder.toString());
    }
}
