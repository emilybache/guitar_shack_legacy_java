package com.guitarshack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Backend {
    String queryBackend(String url) {
        HttpRequest request1 = HttpRequest
                .newBuilder(URI.create(url))
                .build();
        String result1 = "";
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpResponse<String> response1 = null;
        try {
            response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            result1 = response1.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result1;
    }
}
