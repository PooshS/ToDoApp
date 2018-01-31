package ua.poosh.todo.controller;


import ua.poosh.todo.dao.RecordDao;
import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.Validator;

import java.time.LocalDateTime;
import java.util.List;


public class MainControllerImpl implements MainController {

    // Fields and properties:

    private RecordDao recordDao;


    // Constructors:

    public MainControllerImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }


    // Interface methods:


    @Override
    public Record addRecord(Record newRecord) throws AppException {

        //validation
        Validator.validateRecord(newRecord);    // throws AppException !

        return recordDao.save(newRecord);
    }

    @Override
    public Record getRecordById(String id) throws AppException {
        if (id == null) {
            throw new AppException("empty id");
        }

        Record result = recordDao.findOne(id);

        if (result == null) {
            throw new AppException("no records with such id");
        }

        return result;
    }

    @Override
    public List<Record> getAllToDoList(){

        List<Record> allToDoList = recordDao.findAll();
        //System.out.println(allToDoList.get(0));
        return allToDoList;
    }

    @Override
    public Record updateRecord(Record record) throws AppException {
        if (record == null) {
            throw new AppException("record is null");
        }

        Record result = recordDao.update(record);

        if (result == null) {
            throw new AppException("records was not updated");
        }

        return result;
    }

    @Override
    public Record deleteRecord(String key) throws AppException {
        if (key == null) {
            throw new AppException("key is null");
        }

        Record result = recordDao.delete(key);

        if (result == null) {
            throw new AppException("record was not deleted");
        }

        return result;
    }

}
