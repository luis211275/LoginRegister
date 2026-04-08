package dog.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import dog.server.DogService;

import java.io.IOException;
import java.io.OutputStream;


public class DogsController {


    public void handle(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();

        try {




            if (path.startsWith("/dogs/two/breeds/")) {
                String[] partes = path.split("/");
                String r1 = partes[4];
                String r2 = partes[5];

                String result = DogService.Imagen2Razas(r1, r2);
                sendResponse(exchange, 200, result);
                return;
            }


            if (path.startsWith("/dogs/one/breeds/")) {
                String[] partes = path.split("/");
                String raza1 = partes[partes.length - 1];
                String result = DogService.Imagen1Perro(raza1);
                sendResponse(exchange, 200, result);
                return;
            }
            if (path.startsWith("/dogs/n/random/")) {
                String[] partes = path.split("/");
                int n = Integer.parseInt(partes[4]);
                String result = DogService.imagenRandomN(n);
                sendResponse(exchange, 200, result);
                return;
            }

            if (path.equals("/dogs/five/random")) {
                response(exchange, DogService.imagenRandom5());
                return;
            }
            if (path.equals("/dogs/all/images")) {
                response(exchange, DogService.todasImagenes());
                return;
            }
            if (path.equals("/dogs/image/random")) {
                response(exchange, DogService.imagenRandom());
                return;
            }
            if (path.equals("/dogs/noBreeds")) {
                response(exchange, DogService.getPerrosSinBreeds());
                return;
            }
            if (path.equals("/dogs/conBreeds")) {
                response(exchange, DogService.getPerrosConBreeds());
                return;

            }
            if (path.equals("/dogs/list")) {
                response(exchange, DogService.listaEntera());
                return;
            }
            sendResponse(exchange, 404, "Endpoint dogs no válido");
        } catch (Exception e) {
            sendResponse(exchange, 500, "Error llamando a la API dogs");
        }
    }


    private void response (HttpExchange exchange, Object data) throws IOException {
        String respuesta;

        if (data instanceof String) {
            respuesta = (String) data;
        } else {
            respuesta = new Gson().toJson(data);
        }

        sendResponse(exchange, 200, respuesta);
    }

    private void sendResponse(HttpExchange exchange, int status, String body) throws IOException {

        exchange.getResponseHeaders().add("Content-Type", "application/json");

        byte[] bytes = body.getBytes();

        exchange.sendResponseHeaders(status, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}