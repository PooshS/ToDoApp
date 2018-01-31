package ua.poosh.todo.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import ua.poosh.todo.utils.HttpUtils;
import ua.poosh.todo.utils.JsonUtils;

import java.io.File;
import java.io.IOException;


public class StaticFilesHandler implements HttpHandler {

    private final File staticFilesFolder;

    public StaticFilesHandler(String staticFilesPath) {
        this.staticFilesFolder = new File(staticFilesPath);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().toString();

        File file = new File(staticFilesFolder.getAbsolutePath() + query);

        if(file.exists() && file.isFile()){
            String body = JsonUtils.readJsonFromFile(file.getAbsolutePath());
            HttpUtils.sendResponse(httpExchange, body, 200);
        } else {
            HttpUtils.sendResponse(httpExchange, "Static file not found", 404);
        }


    }

}
