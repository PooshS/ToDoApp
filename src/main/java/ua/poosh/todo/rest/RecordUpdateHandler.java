package ua.poosh.todo.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.poosh.todo.controller.MainController;
import ua.poosh.todo.dao.RecordDaoImpl;
import ua.poosh.todo.dao.RecordDaoJsonImpl;
import ua.poosh.todo.exception.AppException;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.JsonUtils;

import java.io.IOException;
import java.io.OutputStream;

public class RecordUpdateHandler implements HttpHandler{
    private MainController mainController;

    public RecordUpdateHandler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        Record record = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), Record.class);

        String recordJsonString = record.getId();
        System.out.println(recordJsonString);
        Record uppRecord;

        try {
            uppRecord = mainController.getRecordById(recordJsonString);
            //System.out.println("todo = " + uppRecord.getToDo() + "; done = " + uppRecord.getDone());
            if (uppRecord.getDone() == false) {
                uppRecord.setDone(true);
            } else {
                uppRecord.setDone(false);
            }
            //System.out.println("todo = " + uppRecord.getToDo() + "; done = " + uppRecord.getDone());
            try {
                Record record2 = mainController.updateRecord(uppRecord);
            } catch (AppException e) {
                e.printStackTrace();
            }

        } catch (AppException e) {
            e.printStackTrace();
        }



        /*try {
            Record saved = mainController.addRecord(record);

            if (saved != null){
                System.out.println("Record '" + record.getToDo() + "' has been saved.");
            }

            String savedRecordJson = JsonUtils.toJson(saved);



            byte[] bytes = savedRecordJson.getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (AppException e) {
            e.printStackTrace();
        }*/

    }
}
