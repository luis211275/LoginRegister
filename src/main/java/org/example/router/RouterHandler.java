package org.example.router;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.controllers.UserController;

import java.io.IOException;
import java.io.OutputStream;

public class RouterHandler implements HttpHandler {

    private final UserController userController = new UserController();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        addCorsHeaders(exchange);

        if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String path = exchange.getRequestURI().getPath();

        if (path.startsWith("/user")) {
            userController.handle(exchange);
            return;
        }

        String response = "404 - Ruta no encontrada";
        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private static void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}
