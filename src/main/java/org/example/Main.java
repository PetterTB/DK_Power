package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {
            // Create the client
            HttpClient client = HttpClient.newHttpClient();

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.energidataservice.dk/dataset/PowerSystemRightNow?limit=5"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject j = new JSONObject(response.body());
            JSONArray records = j.getJSONArray("records");
            ArrayList<PowerEntry> entries = new ArrayList<>();
            for(int i=0; i<records.length(); i++){
                JSONObject record = records.getJSONObject(i);
                System.out.println(record);
                PowerEntry entry = new PowerEntry(record);
                entries.add(entry);
            }
            PowerEntry averagedEntry = new PowerEntry(entries);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}