package GUI01.Project.Database;

import GUI01.Project.GameObject;
import GUI01.Project.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamesDatabase extends Database {


    public Optional<List<GameObject>> getOwnedGames(int user_id) {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.ofNullable(null);
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM games WHERE id IN (SELECT game_id FROM owned_games WHERE user_id = ?)")) {
                ps.setInt(1, user_id);
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
                        games.add(game);
                    }
                    return Optional.of(games);
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
            return Optional.ofNullable(null);
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

    public Optional<List<GameObject>> getGames() {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.ofNullable(null);
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
            return Optional.ofNullable(null);
        }
    }
}
