import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Main {

  public static void sendRequests() {
    HttpClient client = HttpClient.newHttpClient();
    for (int i = 1; i <= 5; i++) {
      String url = "https://api.example.com/resource/" + i;
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

      CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())

        .thenApply(HttpResponse::statusCode)
        .thenAccept(statusCode -> System.out.println("Request " + i + " completed with status code: " + statusCode))
        .exceptionally(throwable -> {
          System.err.println("Request " + i + " failed: " + throwable.getMessage());
          return null;
        });

      future.join();
    }
  }

  public static void main(String[] args) {
  sendRequests();
}
}

