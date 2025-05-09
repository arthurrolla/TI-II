import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class AnaliseSentimento {
    public static void main(String[] args) throws IOException, InterruptedException {
        String endpoint = "https://analisetextoex4.cognitiveservices.azure.com/";
        String chave = "3R5dVKxwM7AFpilTsAC2ERNHRpf9lbLY6qHCNbehAmkbwHfI9dgaJQQJ99BEACLArgHXJ3w3AAAaACOGLe5U";

        String texto = "Eu estou muito feliz por ter feito o exercicio 4 e usar a Azure!";
        String corpoJson = """
        {
          "documents": [
            {
              "language": "pt",
              "id": "1",
              "text": "%s"
            }
          ]
        }
        """.formatted(texto);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint + "text/analytics/v3.0/sentiment"))
            .header("Ocp-Apim-Subscription-Key", chave)
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(corpoJson, StandardCharsets.UTF_8))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Resposta da API:");
        System.out.println(resposta.body());
    }
}
