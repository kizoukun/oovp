package GUI01.Project.Database;

import GUI01.Project.Main;
import GUI01.Project.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase extends Database {

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

}
