package ua.poosh.todo.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.poosh.todo.controller.MainController;
import ua.poosh.todo.model.Record;
import ua.poosh.todo.utils.JsonUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ShowAllRecordsHendler implements HttpHandler {

    MainController mainController;

    public ShowAllRecordsHendler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        List<Record> recordList = mainController.getAllToDoList();

        System.out.println(recordList.size());

        // todo refactor to relative link
        ///String result = JsonUtils.readJsonFromFile("D:\\JavaDev\\ToDoApp\\out\\production\\resources\\data.txt");
        String result = JsonUtils.readJsonFromFile("out/production/resources/data.txt");
        //String result = JsonUtils.readJsonFromFile("data.txt");

        System.out.println("Sent data is -> " + result);

        byte[] bytes = result.getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();

    }
}
