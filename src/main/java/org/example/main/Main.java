package org.example.main;

import com.sun.net.httpserver.HttpServer;
import org.example.conection.ConnectionBBDD;
import org.example.router.RouterHandler;
import org.example.server.UserService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    static void main() throws IOException {
        try {
            ConnectionBBDD.getConnection();
            System.out.println("Conexión correcta a PostgreSQL.");
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
// cada vez que arranca y se pone (http://localhost:8080)
            server.createContext("/", new RouterHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("http://localhost:8080");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        UserService usuario = new UserService();



    }
}