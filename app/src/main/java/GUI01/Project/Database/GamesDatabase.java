package GUI01.Project.Database;

import GUI01.Project.GameObject;
import GUI01.Project.Object.UserGamesObject;
import GUI01.Project.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamesDatabase extends Database {


    public Optional<List<UserGamesObject>> getOwnedGames(int user_id) {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT owned_games.purchase_date, games.* FROM `owned_games` JOIN games ON games.id = owned_games.game_id WHERE owned_games.user_id = ? ORDER BY owned_games.purchase_date DESC")) {
                ps.setInt(1, user_id);
                try (ResultSet rs = ps.executeQuery()) {
                    List<UserGamesObject> games = new ArrayList<>();
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String image = rs.getString("image_uri");
                        double price = rs.getDouble("price");
                        Date date = rs.getDate("added_date");
                        Date purchaseDate = rs.getDate("purchase_date");
                        UserGamesObject game = new UserGamesObject(id, title, price, date, purchaseDate);
                        if(image != null) {
                            game.setImage(image);
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

    public void addOwnedGame(int user_id, int game_id) throws SQLException {
        try (Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to add owned game DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO owned_games (user_id, game_id) VALUES (?, ?)")) {
                ps.setInt(1, user_id);
                ps.setInt(2, game_id);
                ps.executeUpdate();
            }
        }
    }

    public void addGame(String title, String image, double price) throws SQLException {
        try (Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to add game DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO games (title, image_uri, price, added_date) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, title);
                ps.setString(2, image);
                ps.setDouble(3, price);
                ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
                ps.executeUpdate();
            }
        }
    }

    public Optional<GameObject> getGame(int id) {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM games WHERE id = ?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        String title = rs.getString("title");
                        String image = rs.getString("image_uri");
                        double price = rs.getDouble("price");
                        Date date = rs.getDate("added_date");
                        GameObject game = new GameObject(id, title, price, date);
                        if(image != null) {
                            game.setImage(image);
                        }
                        return Optional.of(game);
                    }
                    return Optional.empty();
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<GameObject>> getGames() {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            //select query from games table
            try (Statement stmt = db.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM games")) {
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

    public void deleteGame(int id) throws SQLException {
        try (Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to delete game DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("DELETE FROM games WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public void editGame(int id, String title, String image, double price) throws SQLException {
        try (Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to edit game DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("UPDATE games SET title = ?, image_uri = ?, price = ? WHERE id = ?")) {
                ps.setString(1, title);
                ps.setString(2, image);
                ps.setDouble(3, price);
                ps.setInt(4, id);
                ps.executeUpdate();
            }
        }
    }
}
