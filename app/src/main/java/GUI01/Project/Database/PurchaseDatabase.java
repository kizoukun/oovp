package GUI01.Project.Database;


import GUI01.Project.Main;
import GUI01.Project.Utils;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class PurchaseDatabase extends Database {
    public void insertProductHistory(int gameId, String account) {
        try (Connection db = this.getConn()) {
            if (db == null) {
                Utils.debugLog("Failed to connect to database");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO purchase_history (user_id, games_id, account) VALUES (?, ?, ?)")) {
                ps.setInt(1, Main.authenticatedUser.getId());
                ps.setInt(2, gameId);
                ps.setString(3, account);
                ps.executeUpdate();
            }
        } catch (SQLException err) {
            Utils.debugLog(err.getMessage());
        }
    }

    public Optional<JSONArray> getPurchaseHistoryByUserId(int userId) {
        //get purchase history by user id and join on game table select game name and all purchase history
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            try (PreparedStatement ps = db.prepareStatement("SELECT purchase_history.*, games.title FROM `purchase_history` JOIN games ON games.id = purchase_history.games_id WHERE purchase_history.user_id = ?")) {
                ps.setInt(1, userId);
                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    JSONArray purchaseHistory = new JSONArray();
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        int gamesId = rs.getInt("games_id");
                        String account = rs.getString("account");
                        String title = rs.getString("title");
                        Date date = new Date(rs.getTimestamp("created_at").getTime());
                        purchaseHistory.put(new org.json.JSONObject().put("id", id).put("games_id", gamesId).put("account", account).put("title", title).put("date", Utils.formatDate(date)));
                    }
                    return Optional.of(purchaseHistory);
                } catch (JSONException e) {
                    Utils.debugLog(e.getMessage());
                    return Optional.empty();
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
            return Optional.empty();
        }
    }
}
