/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezab
 */
public class Database {
    private static Connection conn = null;
    
    private static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/oovp";
        String username = "root";
        String password = "";
        try {
            if(conn == null || conn.isClosed()) {
                Database.conn = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get connection");
            System.out.println(e.getMessage());
        } finally {
            return Database.conn;
        }
    }
    
    public static ResultSet getUsers() {
        Connection db = null;
        Statement stmt = null;
        try {
            db = Database.getConnection();
            if(db == null) {
                return null;
            }
            stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            return rs;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if(stmt != null) stmt.close();
                if(db != null) db.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean isUserExists(String email) {
        return Database.getUser(email).isPresent();
    }
    
    public static void createUser(String email, String firstName, String lastName, String password, String gender) throws SQLException {
        try (java.sql.Connection db = Database.getConnection()) {
            if(db == null) {
                System.out.println("Failed to create user DB Error.");
                return;
            }
            
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO users (email, first_name, last_name, password, gender) VALUES (?, ?, ?, ?, ?)")) {
                ps.setString(1, email);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, password);
                ps.setString(5, gender);
                ps.executeUpdate();
            }
        }
    }
    
    public static Optional<User> getUser(String email) {
        try(Connection db = Database.getConnection()) {
            if(db == null) {
                return Optional.ofNullable(null);
            }
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM users WHERE email = ?")) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        int id = rs.getInt("id");
                        String emails = rs.getString("email");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String gender = rs.getString("gender");
                        String password = rs.getString("password");
                        List<Balance> balances = Database.getUserBalance(id);
                        User user = new User(id, emails, firstName, lastName, gender, password, balances);
                        return Optional.ofNullable(user);
                    }
                    return Optional.ofNullable(null);
                }
            }
            
        } catch (SQLException err) {
            return Optional.ofNullable(null);
        }
    }

    public static List<Balance> getUserBalance(int user_id) {
        List<Balance> balances = new ArrayList<>();
        try(Connection db = Database.getConnection()) {
            if(db == null) {
                return balances;
            }
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM balance_histories WHERE user_id = ?")) {
                ps.setInt(1, user_id);
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        int type = rs.getInt("type");
                        double amount = rs.getDouble("amount");
                        double balance_after = rs.getDouble("balance_after");
                        double balance_before = rs.getDouble("balance_before");
                        String description = rs.getString("description");
                        Date date = rs.getDate("created_at");
                        Balance balance = new Balance(id, amount, balance_after, balance_before, type, description, date);
                        balances.add(balance);
                    }
                    return balances;
                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return balances;
        }
    }

    public static Optional<List<GameObject>> getGames() {
        try(Connection db = Database.getConnection()) {
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
            System.out.println(err.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public static void addGame(String title, String image, double price) throws SQLException {
        try (Connection db = Database.getConnection()) {
            if(db == null) {
                System.out.println("Failed to add game DB Error.");
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

    //add owned game
    public static void addOwnedGame(int user_id, int game_id) throws SQLException {
        try (Connection db = Database.getConnection()) {
            if(db == null) {
                System.out.println("Failed to add owned game DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO owned_games (user_id, game_id) VALUES (?, ?)")) {
                ps.setInt(1, user_id);
                ps.setInt(2, game_id);
                ps.executeUpdate();
            }
        }
    }

    //get user owned game
    public static Optional<List<GameObject>> getOwnedGames(int user_id) {
        try(Connection db = Database.getConnection()) {
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
            System.out.println(err.getMessage());
            return Optional.ofNullable(null);
        }
    }

    //insert balance history
    public static void insertBalanceHistory(int user_id, int type, double amount, double balance_after, double balance_before, String description) throws SQLException {
        try (Connection db = Database.getConnection()) {
            if(db == null) {
                System.out.println("Failed to add balance history DB Error.");
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO balance_histories (user_id, type, amount, balance_after, balance_before, description) VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, user_id);
                ps.setInt(2, type);
                ps.setDouble(3, amount);
                ps.setDouble(4, balance_after);
                ps.setDouble(5, balance_before);
                ps.setString(6, description);
                ps.executeUpdate();
            }
        }
    }
}
