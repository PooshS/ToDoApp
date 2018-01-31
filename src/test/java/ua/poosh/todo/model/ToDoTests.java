package ua.poosh.todo.model;

import org.junit.Assert;
import org.junit.Test;
import ua.poosh.todo.model.Record;

public class ToDoTests {

    @Test
    public void test (){
        Record record = new Record();
        record.setToDo("todo");

        Assert.assertEquals("todo", record.getToDo());
    }


}
