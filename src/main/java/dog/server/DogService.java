package dog.server;

import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class DogService {
    /**
     * metodo donde hara el metodo de llamadaBucleSinBreeds
     *
     * @return llamadaBucleSinBreeds
     * @throws Exception exception
     */
    public static List<String> getPerrosSinBreeds() throws Exception {
        return llamadaBucleSinBreeds("https://dog.ceo/api/breeds/list/all");
    }


    /**
     * metodo donde hara el metodo de llamadaBucleConBreeds
     *
     * @return getPerrosConBreeds
     * @throws Exception exception
     */

    public static List<String> getPerrosConBreeds() throws Exception {
        return llamadaBucleConBreeds("https://dog.ceo/api/breeds/list/all");

    }


    /**
     * metodo donde hara el metodo de llamada con la url introducida
     *
     * @return llamada
     * @throws Exception exception
     */

    public static String todasImagenes() throws Exception {
        return llamada("https://dog.ceo/api/breed/akita/images");

    }


    /**
     * metodo donde hace el metodo de llamada con la url introducida
     *
     * @return llamada
     * @throws Exception exception
     */
    public static String imagenRandom() throws Exception {
        return llamada("https://dog.ceo/api/breeds/image/random");
    }

    /**
     * metodo donde hace el metodo de llamada con la url introducida
     *
     * @return llamada
     * @throws Exception exception
     */
    public static String imagenRandom5() throws Exception {
        return llamada("https://dog.ceo/api/breeds/image/random/5");
    }


    /**
     * metodo donde hace el metodo de llamada con la url introducida introduciendo n, que dependiendo del numero que introduzca cambiara el numero de fotos
     *
     * @return llamada
     * @throws Exception exception
     */
    public static String imagenRandomN(int n) throws Exception {
        return llamada("https://dog.ceo/api/breeds/image/random/" + n);
    }

    /**
     * metodo donde hace el metodo de llamada con la url introducida
     *
     * @return llamada
     * @throws Exception exception
     */
    public static String listaEntera() throws Exception {
        return llamada("https://dog.ceo/api/breeds/list/all");
    }


    /**
     * Metodo que tte devolvera todas las imagenes de la raza que introduzcas
     *
     * @param raza1 la raza que introduzcas
     * @return llamada
     * @throws Exception exception
     */
    public static String Imagen1Perro(String raza1) throws Exception {
        return llamada("https://dog.ceo/api/breed/" + raza1 + "/images");
    }


    public static String Imagen2Razas(String raza1, String raza2) throws Exception {
        // 1. Realizamos las llamadas a la API para cada raza
        String foto1 = llamada("https://dog.ceo/api/breed/" + raza1 + "/images/random/1");
        String foto2 = llamada("https://dog.ceo/api/breed/" + raza2 + "/images/random/1");

        return llamada(foto1 + foto2);
    }


    /**
     * clase llamada que crea el httpclient y buildea el HttpRerquest
     *
     * @param url el enlace que quieras introducir
     * @return response body
     * @throws Exception exception
     */
    public static  String llamada(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


    /**
     * Llamada que calcula si algun perro tiene breeds, y si no las tiene, las guarda en un array
     *
     * @param url la url que introduzcas
     * @return perrosSinBreeds
     * @throws Exception exception
     */
    public static List<String> llamadaBucleSinBreeds(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        JsonObject message = jsonObject.getAsJsonObject("message");

        List<String> perrosSinBreeds = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : message.entrySet()) {
            JsonArray jsonArray = entry.getValue().getAsJsonArray();

            if (jsonArray.isEmpty()) {
                perrosSinBreeds.add(entry.getKey());
            }
        }
        return perrosSinBreeds;

    }

    /**
     * Llamada que calcula si algún perro tiene breeds, y si las tiene, las guarda en el array
     *
     * @param url la url que introduzcas
     * @return perros con breeds
     * @throws Exception exception
     */

    public static List<String> llamadaBucleConBreeds(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        JsonObject message = jsonObject.getAsJsonObject("message");

        List<String> perrosConBreeds = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : message.entrySet()) {
            JsonArray jsonArray = entry.getValue().getAsJsonArray();

            if (!jsonArray.isEmpty()) {
                perrosConBreeds.add(entry.getKey());
            }
        }
        return perrosConBreeds;
    }
}