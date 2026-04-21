import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

void main() throws Exception {
  HttpClient client = HttpClient.newHttpClient();
  HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://catfact.ninja/fact"))
          .GET()
          .build();

  HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

  String json = response.body();
  String fact = json.split("\"fact\":\"")[1].split("\"")[0];
  int length = Integer.parseInt(json.split("\"length\":")[1].split("}")[0]);
  
  CatFact catFact = new CatFact(fact, length);
  IO.println(catFact);
}
