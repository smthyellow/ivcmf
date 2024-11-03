package com.example.ivcmf.web.Util;

import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RestController
public class APIConnector {

    public String getJson(URL url) {

        StringBuilder jsonString = new StringBuilder();

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    jsonString.append(scanner.nextLine());
                }
                scanner.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString.toString();
    }


}
