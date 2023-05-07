package GUI01.Project.Database;

import GUI01.Project.GameObject;
import GUI01.Project.Main;
import GUI01.Project.Utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartsDatabase extends Database {

    public void insertCart(int transactionId, int gameId) {
        try (Connection db = this.getConn()) {
            if (db == null) {
                Utils.debugLog("Failed to connect to database");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO carts (transaction_id, status, games_id) VALUES (?, ?, ?)")) {
                ps.setInt(1, transactionId);
                ps.setString(2, "PENDING");
                ps.setInt(3, gameId);
                ps.executeUpdate();
            }
        } catch (SQLException err) {
            Utils.debugLog(err.getMessage());
        }
    }

    public Optional<List<GameObject>> getCartGames(int transactionId) {
        //get cart games join on games table
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            try (PreparedStatement ps = db.prepareStatement("SELECT carts.id as cart_id,  games.* FROM `carts` JOIN games ON games.id = carts.games_id WHERE carts.transaction_id = ?")) {
                ps.setInt(1, transactionId);
                try (ResultSet rs = ps.executeQuery()) {
                    List<GameObject> games = new ArrayList<>();
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String image = rs.getString("image_uri");
                        double price = rs.getDouble("price");
                        Date date = rs.getDate("added_date");
                        GameObject game = new GameObject(id, title, price, date);
                        if(image != null) {
                            game.setImage(image);
                        }
                        String cartId = rs.getString("cart_id");
                        if(cartId != null) {
                            game.setCartId(Integer.parseInt(cartId));
                        }
                        games.add(game);
                    }
                    return Optional.of(games);
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
            return Optional.empty();
        }
    }


    public void updateCartsStatusById(int id) {
        try (Connection db = this.getConn()) {
            if (db == null) {
                Utils.debugLog("Failed to connect to database");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("UPDATE carts SET status = 'SEND' WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException err) {
            Utils.debugLog(err.getMessage());
        }
    }
}
