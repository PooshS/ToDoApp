package ua.poosh.todo;

import com.sun.net.httpserver.HttpServer;
import ua.poosh.todo.config.ConfigHolder;
import ua.poosh.todo.controller.MainController;
import ua.poosh.todo.controller.MainControllerImpl;
import ua.poosh.todo.dao.RecordDaoJsonImpl;
import ua.poosh.todo.rest.*;

import java.io.File;
import java.net.InetSocketAddress;

public class Server {

    private static final String CONFIG_FILE_PATH = "/app.properties";

    public Server() {
    }

    public static void main(String[] args) throws Exception {

        ConfigHolder ch = new ConfigHolder(
                new File(Server.class.getResource(CONFIG_FILE_PATH).getFile()).getAbsolutePath());

        File file = new File(Server.class.getResource(ch.getProperty("app.db.path")).getFile());

        MainController mainController = new MainControllerImpl(new RecordDaoJsonImpl(file.getAbsolutePath()));

        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(Integer.parseInt(ch.getProperty("app.port"))), 0);

        System.out.println(Integer.parseInt(ch.getProperty("app.port")));

        server.createContext("/show-all-records", new ShowAllRecordsHendler(mainController));

        server.createContext("/add-record", new AddRecordHandler(mainController));

        server.createContext("/upp-record", new RecordUpdateHandler(mainController));

        server.createContext("/del-record", new RecordDelHandler(mainController));

        File staticFolder =
                new File(Server.class.getResource(ch.getProperty("app.web.static")).getFile());

        server.createContext("/", new StaticFilesHandler(staticFolder.getAbsolutePath()));

        server.setExecutor(command -> {
            try{
                command.run();
            }catch (Throwable ex){
                ex.printStackTrace();
            }
        });
        server.start();

        System.out.println("Server has been started");    }

}
