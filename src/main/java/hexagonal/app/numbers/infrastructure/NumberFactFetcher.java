package hexagonal.app.numbers.infrastructure;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class NumberFactFetcher {

    HttpClient client;

    public NumberFactFetcher() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetch(long number) {
        try {
            fetchThrowing(number);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    private void fetchThrowing(long number) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://numbersapi.com/" + number))
                .build();
        String response = client.send(request, ofString()).body();
        System.out.println("response = " + response);
    }
}
