package sv.arrupe.apinasa.service;

import com.google.gson.Gson;
import sv.arrupe.apinasa.model.ApodResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NasaService { // 3 usages

    private static final String API_KEY = "DEMO_KEY"; // usage

    public ApodResponse getApod(String date) throws IOException, InterruptedException { // usage

        String url = "https://api.nasa.gov/planetary/apod?api_key="
                + API_KEY + "&date=" + date;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        return gson.fromJson(response.body(), ApodResponse.class);
    }
}