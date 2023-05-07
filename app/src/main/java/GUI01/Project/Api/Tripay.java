/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project.Api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author rezab
 */
public class Tripay {

    private static String URL = "https://tripay.co.id/api-sandbox/transaction";
    private static String APIKEY = "DEV-hPgaCkFu8CMv6114TINjyLs931mAyGPeKHpBEySM";
    private static String PRIVATE_KEY = "lMmze-f4BDO-7yGsh-EjN9X-vRsno";
    private static String MERCHANT_CODE = "T9701";
    private static String MERCHANT_REF = "Merchant Sandbox";
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    public static JSONObject getTransaction(String id) throws Exception {
        URL getUrl = new URL(URL + "/detail?reference=" + id);
        HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + APIKEY);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject json = new JSONObject(response.toString());
            if (json.getBoolean("success")) {
                return json.getJSONObject("data");
            } else {
                throw new Exception(json.getString("message"));
            }
        }
    }

    public static JSONObject createTransaction(int amount, String paymentType, String email, String fullName) throws Exception {
        URL url = new URL(URL + "/create");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        JSONObject body = new JSONObject();
        body.put("method", paymentType);
        body.put("amount", amount);
        body.put("customer_name", fullName);
        body.put("customer_email", email);
        body.put("merchant_ref", MERCHANT_REF);
        JSONArray orderItems = new JSONArray();
        JSONObject orderItem = new JSONObject();
        orderItem.put("name", "Top Up");
        orderItem.put("price", amount);
        orderItem.put("quantity", 1);
        orderItem.put("price", amount);
        orderItems.put(orderItem);
        body.put("order_items", orderItems);
        body.put("signature",generateSignature(PRIVATE_KEY, MERCHANT_CODE + MERCHANT_REF + amount));
        byte[] requestBodyBytes = body.toString().getBytes(StandardCharsets.UTF_8);
        con.setDoOutput(true);
        con.setRequestProperty("User-Agent", userAgent);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + APIKEY);
        con.setRequestProperty("Content-Length", Integer.toString(requestBodyBytes.length));
        OutputStream outputStream = con.getOutputStream();
        outputStream.write(requestBodyBytes);
        if (con.getResponseCode() == 400) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject json = new JSONObject(response.toString());
                if (json.getBoolean("success")) {
                    return json.getJSONObject("data");
                } else {
                    throw new Exception(json.getString("message"));
                }
            }
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject json = new JSONObject(response.toString());
                if (json.getBoolean("success")) {
                    return json.getJSONObject("data");
                } else {
                    throw new Exception(json.getString("message"));
                }
            }
        }

    }

    public static String generateSignature(String privateKey, String body) {
        return Hashing.hmacSha256(privateKey.getBytes(StandardCharsets.UTF_8))
                .newHasher()
                .putString(body, StandardCharsets.UTF_8)
                .hash()
                .toString();
    }
}
