package ua.poosh.todo.dao;

import ua.poosh.todo.model.Record;

import java.util.HashMap;
import java.util.Map;


public class AppDataContainer {

    public Map<String,Record> recordMap;

    public AppDataContainer() {
        this.recordMap = new HashMap<>();
    }
}
