package ua.poosh.todo.dao;


import ua.poosh.todo.model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecordDaoImpl implements RecordDao {

    private AppDataContainer appDataContainer;

    public RecordDaoImpl(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public Record save(Record record) {

        String generatedKey = UUID.randomUUID().toString().substring(5);

        record.setId(generatedKey);
        appDataContainer.recordMap.put(generatedKey, record);

        return record;
    }

    @Override
    public Record findOne(String s) {
        return appDataContainer.recordMap.get(s);
    }

    @Override
    public List<Record> findAll() {
        return new ArrayList<>(appDataContainer.recordMap.values());
    }

    @Override
    public Record delete(String s) {
        return appDataContainer.recordMap.remove(s);
    }

    @Override
    public Record update(Record record) {
        return appDataContainer.recordMap.entrySet().stream()
                .filter(el ->
                        el.getValue().getId().equals(record.getId())
                )
                .findFirst().get().setValue(record);
    }
}
