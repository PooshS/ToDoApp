package ua.poosh.todo.utils;


import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;

import java.util.List;

public class Validator {

    /**
     * This method validates new record before storing into DB.
     * @param record new record, which must not be null,
     *               must have not null and not empty title,
     *               not null and not empty body.
     * @throws AppException when record is null, its title is null or empty,
     * or its body is null or empty.
     *
     * @see Record
     * @see AppException
     * */

    public static void validateRecord(Record record) throws AppException {

        if(record == null){
            throw new AppException("empty record");
        }

        if(record.getToDo() == null || record.getToDo().isEmpty()){
            throw new AppException("empty body or title");
        }
    }

}
