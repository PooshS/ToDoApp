package ua.poosh.todo.controller;


import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;

import java.util.List;


public interface MainController {

    Record addRecord(Record newRecord) throws AppException;

    /**
     * This method saves new todo record into DB.
     * @param newRecord record we want to save into DB.
     * @return reference to new record stored in DB.
     * @throws AppException when has problems with validation.
     *
     * @see Record
     * @see AppException
     * *//*
    Record addRecord(long userId, String dairyId, Record newRecord) throws AppException;*/

    /**
     * This method searches in DB for todo record with such id.
     * @param id unique identifier of todo record we want to find.
     * @return reference to found in DB todo record.
     * @throws AppException if DB doesn't store todo record with such id.
     *
     * @see Record
     * @see AppException
     * */
    Record getRecordById(String id) throws AppException;

    List<Record> getAllToDoList();

    Record updateRecord(Record record) throws AppException;

    Record deleteRecord(String key) throws AppException;

}
