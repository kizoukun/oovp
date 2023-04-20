/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;
import java.sql.*;
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
            return stmt.executeQuery("SELECT * FROM users");
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
        return Database.getUser(email) != null;
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
                        String emails = rs.getString("email");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String gender = rs.getString("gender");
                        String password = rs.getString("password");
                        User user = new User(emails, firstName, lastName, gender, password);
                        return Optional.ofNullable(user);
                    }
                    return Optional.ofNullable(null);
                }
            }
            
        } catch (SQLException err) {
            return Optional.ofNullable(null);
        }
    }
}
