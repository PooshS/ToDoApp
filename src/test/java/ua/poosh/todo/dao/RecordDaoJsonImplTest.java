package ua.poosh.todo.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.poosh.todo.model.Record;

import java.util.List;

import static org.junit.Assert.*;

public class RecordDaoJsonImplTest {

    Record record;
    RecordDaoJsonImpl recordDaoJson;

    @Before
    public void SetUp(){
        record = new Record("111", false);
        record.setId("test");
        recordDaoJson = new RecordDaoJsonImpl("out/production/resources/data.txt");

    }

    @After
    public void tearDown(){
        recordDaoJson.delete(record.getId());
    }

    @Test
    public void save() throws Exception {

        record = recordDaoJson.save(record);
        assertNotEquals("test", record.getId());
    }

    @Test
    public void findOne() throws Exception {

        record = recordDaoJson.save(record);
        Record findOneRecord = recordDaoJson.findOne(record.getId());
        assertEquals(record, findOneRecord);

    }

    @Test
    public void delete() throws Exception {

        record = recordDaoJson.save(record);
        Record deletedRecord = recordDaoJson.delete(record.getId());
        assertEquals(record.getId(), deletedRecord.getId());

    }

    @Test
    public void findAll() throws Exception {
        List<Record> records = recordDaoJson.findAll();
        int listSizeBefore = records.size();
        record = recordDaoJson.save(record);
        Record record1 = recordDaoJson.save(new Record("222", true));
        records = recordDaoJson.findAll();
        assertTrue((listSizeBefore + 2) == records.size());
        recordDaoJson.delete(record1.getId());
    }

    @Test
    public void update() throws Exception {

        record = recordDaoJson.save(record);
        Record updatedRecord = new Record("111", true);
        updatedRecord.setId(record.getId());
        updatedRecord = recordDaoJson.update(updatedRecord);
        assertNotEquals(record.getDone(), updatedRecord.getDone());

    }

}