/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project.Database;
import GUI01.Project.Balance;
import GUI01.Project.GameObject;
import GUI01.Project.User;
import GUI01.Project.Utils;

import java.sql.*;
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

    public Connection getConn() {
        String url = "jdbc:mysql://localhost:3306/oovp";
        String username = "root";
        String password = "";
        try {
            if(conn == null || conn.isClosed()) {
                Database.conn = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            Utils.debugLog("Failed to get connection \n" + e.getMessage());
        } finally {
            return Database.conn;
        }
    }


}
