package GUI01.Project.Api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Account {

    public static String getAccount(int gameId) throws Exception {
        URL url = new URL("http://localhost:4000/account?game_id=" + gameId);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("authorization", "Bearer test");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject json = new JSONObject(response.toString());
            if (json.getBoolean("success")) {
                return json.getString("data");
            } else {
                throw new Exception(json.getString("message"));
            }
        }
    }

}
