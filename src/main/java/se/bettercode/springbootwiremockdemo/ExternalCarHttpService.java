package se.bettercode.springbootwiremockdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

class ExternalCarHttpService {

  String getCarByIdOverHttp(int id) throws IOException {
    URL url = new URL("http://localhost:8089/car/" + id);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : "
          + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    String json = br.lines().collect(Collectors.joining());
    conn.disconnect();
    return json;
  }

}
