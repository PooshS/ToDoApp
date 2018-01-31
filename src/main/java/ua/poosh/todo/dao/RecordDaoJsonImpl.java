package ua.poosh.todo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.*;

public class RecordDaoJsonImpl implements RecordDao {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String RECORDS_PATH;


    private Type recordListType = new TypeToken<List<Record>>(){}.getType();
    private List<Record> recordList;

    public RecordDaoJsonImpl(String path) {
        RECORDS_PATH = path;

        String recordListJson = "";
        recordList = gson.fromJson(recordListJson, recordListType);

    }

    @Override
    public Record save(Record record) {

        String generatedKey = UUID.randomUUID().toString().substring(5);

        record.setId(generatedKey);

        String recordsJson = JsonUtils.readJsonFromFile(RECORDS_PATH);
        Type mapType = new TypeToken<Map<String,Record>>(){}.getType();

        Map<String,Record> records = gson.fromJson(recordsJson, mapType);
        if(records == null) {
            records = new HashMap<>();
        }

        records.put(generatedKey,record);
        recordsJson = gson.toJson(records, mapType);

        if (!JsonUtils.writeJsonToFile(RECORDS_PATH, recordsJson)) {
            return null;
        }

        return record;
    }

    @Override
    public Record findOne(String key) {

        String recordsJson = JsonUtils.readJsonFromFile(RECORDS_PATH);
        Type mapType = new TypeToken<Map<String,Record>>(){}.getType();

        Map<String,Record> records = gson.fromJson(recordsJson, mapType);

        if(records.containsKey(key)) {
            return records.get(key);
        }

        return null;
    }

    @Override
    public List<Record> findAll() {

        String recordsJson = JsonUtils.readJsonFromFile(RECORDS_PATH);
        Type mapType = new TypeToken<Map<String,Record>>(){}.getType();

        Map<String,Record> records = gson.fromJson(recordsJson, mapType);

        if (records == null){
            return null;
        }

        List<Record> allRecords = new ArrayList<Record>();

        for (String key : records.keySet()) {
            allRecords.add(records.get(key));
        }

        return allRecords;
    }

    @Override
    public Record delete(String key) {

        String recordsJson = JsonUtils.readJsonFromFile(RECORDS_PATH);
        Type mapType = new TypeToken<Map<String,Record>>(){}.getType();

        Map<String,Record> records = gson.fromJson(recordsJson, mapType);

        if(records.containsKey(key)) {

            Record delRecord = records.get(key);

            records.remove(key);

            recordsJson = gson.toJson(records, mapType);

            if (!JsonUtils.writeJsonToFile(RECORDS_PATH, recordsJson)) {
                return null;
            }
            return delRecord;
        }

        return null;
    }

    @Override
    public Record update(Record record) {

        String recordsJson = JsonUtils.readJsonFromFile(RECORDS_PATH);
        Type mapType = new TypeToken<Map<String, Record>>(){}.getType();

        Map<String, Record> records = gson.fromJson(recordsJson, mapType);

        if (records.containsValue(record)) {
            for (String key : records.keySet()) {
                Record oldRecord = records.get(key);
                if (oldRecord.equals(record)) {
                    records.replace(key, oldRecord, record);
                    break;
                }
            }


            recordsJson = gson.toJson(records, mapType);

            if (!JsonUtils.writeJsonToFile(RECORDS_PATH, recordsJson)) {
                return null;
            }
            return record;
        }

        return null;
    }
}
