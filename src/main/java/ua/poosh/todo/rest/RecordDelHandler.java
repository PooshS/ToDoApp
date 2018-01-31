package ua.poosh.todo.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.poosh.todo.controller.MainController;
import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.JsonUtils;

import java.io.IOException;

public class RecordDelHandler implements HttpHandler {

    private MainController mainController;

    public RecordDelHandler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        Record record = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), Record.class);

        String recordJsonString = record.getId();
        System.out.println(recordJsonString);
        Record delRecord;

        try {
            delRecord = mainController.deleteRecord(recordJsonString);
            System.out.println("deleted record -> todo = " + delRecord.getToDo() + "; done = " + delRecord.getDone());

            //System.out.println("todo = " + uppRecord.getToDo() + "; done = " + uppRecord.getDone());
        } catch (AppException e) {
            e.printStackTrace();
        }
    }
}
