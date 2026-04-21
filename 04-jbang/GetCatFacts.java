///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.json:json:20251224
//JAVA 25+

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

void main() throws Exception {
  HttpClient client = HttpClient.newHttpClient();
  HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://catfact.ninja/fact"))
          .GET()
          .build();

  HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

  JSONObject json = new JSONObject(response.body());
  CatFact catFact = new CatFact(json.getString("fact"), json.getInt("length"));
  IO.println(catFact);
}