package ua.poosh.todo.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.poosh.todo.dao.RecordDao;
import ua.poosh.todo.dao.RecordDaoJsonImpl;
import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainControllerImplTest {

    private MainController mainController;

    @Before
    public void SetUp() {
        /*RecordDao recordDao = new RecordDaoJsonImpl("out/production/resources/data.txt");
        mainController = new MainControllerImpl(recordDao);*/

        RecordDao mockRecordDao = mock(RecordDao.class);
        when(mockRecordDao.save(any(Record.class))).thenReturn(new Record("1", false));
        when(mockRecordDao.findAll()).thenReturn(Arrays.asList(new Record("2",false)));
        when(mockRecordDao.findOne(anyString())).thenReturn(new Record("3", true));
        when(mockRecordDao.update(any(Record.class))).thenReturn(new Record());
        when(mockRecordDao.delete(anyString())).thenReturn(new Record("5",true));

        mainController = new MainControllerImpl(mockRecordDao);
    }

    @Test
    public void addRecord() throws Exception {
        /*int siz;
        if(mainController.getAllToDoList() != null) {
            siz = mainController.getAllToDoList().size();
        }else siz = 0;
        Record record = new Record("test", false);
        mainController.addRecord(record);
        siz = mainController.getAllToDoList().size();
        String id = record.getId();
        mainController.deleteRecord(id);
        siz = mainController.getAllToDoList().size();
        if(siz == 0) throw new AppException("method \"mainController.addRecord\" is not work");*/

        Record record = mainController.addRecord(new Record(" ",false));
        assertNotNull(record);
        assertEquals("1", record.getToDo());
        Assert.assertNull(new Record().getToDo());
    }

    @Test
    public void getRecordById() throws Exception {
        Record record = mainController.getRecordById("");
        assertEquals("3", record.getToDo());

    }

    @Test
    public void getAllToDoList() throws Exception {
         List<Record> list = mainController.getAllToDoList();
         assertNotNull(list);
    }

    @Test
    public void updateRecord() throws Exception {
        Record record = mainController.updateRecord(new Record());
        assertEquals(false, record.getDone());
    }

    @Test
    public void deleteRecord() throws Exception {
        Record record = mainController.deleteRecord("");
        assertEquals("5", record.getToDo());
    }

}