package dao;

import constants.IConstants;
import dao.file_handler.FileHandler;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class TenantLetterDAOImpl implements HandlerDAO<TenantLetter>{
    private static final List<TenantLetter> TENANT_LETTERS = new ArrayList<>();

    @Override
    public void loadData() throws Exception {
        //loads data in the list only one time
        if(TENANT_LETTERS.isEmpty()) {
            List<String> data = FileHandler.fetchData();
            data.forEach(line -> {
                if (line.startsWith(IConstants.TENANT_LETTER_RECORD)) {
                    String[] lineParts = line.replace(IConstants.TENANT_LETTER_RECORD, "").split(IConstants.VALUE_SEPARATOR);
                    TenantLetter newTenantLetter = new TenantLetter();
                    newTenantLetter.setId(Integer.parseInt(lineParts[0]));
                    newTenantLetter.setContent(lineParts[1]);
                    TENANT_LETTERS.add(newTenantLetter);
                }
            });
        }
    }

    @Override
    public int size() {
        return TENANT_LETTERS.size();
    }

    @Override
    public boolean save(TenantLetter obj) {
        return TENANT_LETTERS.add(obj);
    }

    @Override
    public TenantLetter getByIndex(int index) {
        return TENANT_LETTERS.get(index);
    }

    @Override
    public TenantLetter getById(int id) {
        return TENANT_LETTERS.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateByIndex(int index, TenantLetter obj) {
        return TENANT_LETTERS.set(index, obj) != null;
    }

    @Override
    public boolean updateById(int id, TenantLetter obj) {
        return TENANT_LETTERS.set(TENANT_LETTERS.indexOf(getById(id)), obj) != null;
    }

    @Override
    public boolean deleteByIndex(int index) {
        return TENANT_LETTERS.remove(index) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return TENANT_LETTERS.remove(TENANT_LETTERS.indexOf(getById(id))) != null;
    }

    @Override
    public void updateData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        TenantLetter tenantLetter;
        boolean isVolume;
        for (int i = 0; i < TENANT_LETTERS.size(); i++) {
            tenantLetter = TENANT_LETTERS.get(i);
            stringBuilder.append(IConstants.TENANT_LETTER_RECORD);
            stringBuilder.append(tenantLetter.getId());
            stringBuilder.append(IConstants.VALUE_SEPARATOR);
            stringBuilder.append(tenantLetter.getContent());
            if(i < TENANT_LETTERS.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        FileHandler.updateFile(stringBuilder.toString());
    }
}
