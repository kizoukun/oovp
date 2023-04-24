package GUI01.Project.Database;

import GUI01.Project.Balance;
import GUI01.Project.Main;
import GUI01.Project.User;
import GUI01.Project.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDatabase extends Database {

    public void updateFullName(String firstName, String lastName) {
        String query = "UPDATE users SET first_name = ?, last_name = ? WHERE id = ?";
        try (Connection db = this.getConn()) {
            try (PreparedStatement ps = db.prepareStatement(query)) {
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setInt(3, Main.authenticatedUser.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Utils.debugLog(e.getMessage());
        }
    }

    public void updatePassword(String hashedPassword) {
        String query = "UPDATE users SET password = ? WHERE id = ?";
        try (Connection db = this.getConn()) {
            try (PreparedStatement ps = db.prepareStatement(query)) {
                ps.setString(1, hashedPassword);
                ps.setInt(2, Main.authenticatedUser.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Utils.debugLog(e.getMessage());
        }
    }

    public void insertBalanceHistory(int user_id, int type, double amount, double balance_after, double balance_before, String description) throws SQLException {
        try (Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to add balance history DB Error.");
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

    public ResultSet getUsers() {
        Connection db = null;
        Statement stmt = null;
        try {
            db = this.getConn();
            if(db == null) {
                return null;
            }
            stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            return rs;
        } catch (SQLException e) {
            Utils.debugLog(e.getMessage());
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

    public void createUser(String email, String firstName, String lastName, String password, String gender) throws SQLException {
        try (java.sql.Connection db = this.getConn()) {
            if(db == null) {
                Utils.debugLog("Failed to create user DB Error.");
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

    public boolean isUserExists(String email) {
        return this.getUser(email).isPresent();
    }

    public Optional<User> getUser(String email) {
        try(Connection db = this.getConn()) {
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
                        List<Balance> balances = Main.usersDb.getUserBalance(id);
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

    public List<Balance> getUserBalance(int user_id) {
        List<Balance> balances = new ArrayList<>();
        try(Connection db = this.getConn()) {
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
            Utils.debugLog(err.getMessage());
            return balances;
        }
    }

}
