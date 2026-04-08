package dog.main;


import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.Scanner;

import dog.router.RouterHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
// Se crea el servior y desde qué puerto va estar escuchando
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
// cada vez que arranca y se pone (http://localhost:8080)
        server.createContext("/", new RouterHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("http://localhost:8080/register");
        }
    }
