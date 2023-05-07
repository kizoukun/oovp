package GUI01.Project.Database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import GUI01.Project.Main;
import GUI01.Project.Object.Transactions;
import GUI01.Project.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

/**
 *
 * @author rezab
 */
public class TransactionsDatabase extends Database {

    public Optional<List<Transactions>> getTransactions() {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.empty();
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM transactions")) {
                try (ResultSet rs = ps.executeQuery()) {
                    List<Transactions> transactions = new ArrayList<>();
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        int userId = rs.getInt("user_id");
                        String description = rs.getString("description");
                        String paymentType = rs.getString("payment_type");
                        double amount = rs.getDouble("amount");
                        String qris = rs.getString("qris");
                        String instructions = rs.getString("instructions");
                        String transactionApiId = rs.getString("transaction_id");
                        String status = rs.getString("status");
                        Date date = new Date(rs.getTimestamp("expired_at").getTime());
                        Date createdAt = new Date(rs.getTimestamp("expired_at").getTime());
                        Transactions transaction = new Transactions(id, userId, amount, description, paymentType, status, transactionApiId, date, createdAt);
                        if(qris != null) {
                            transaction.setQris(qris);
                        }
                        JSONArray instruction = new JSONArray(instructions);
                        for(int i = 0; i < instruction.length(); i++) {
                            JSONObject obj = instruction.getJSONObject(i);
                            for (Iterator it = obj.keys(); it.hasNext(); ) {
                                List<String> steps = new ArrayList<>();
                                JSONArray step = obj.getJSONArray("steps");
                                for(int j = 0; j < step.length(); j++) {
                                    steps.add(step.getString(j));
                                }
                                transaction.addPaymentInstructions(obj.getString("title"),steps);
                            }
                        }
                        transactions.add(transaction);
                    }
                    return Optional.of(transactions);
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

    public Transactions getTransactionById(int id) {
        try(Connection db = this.getConn()) {
            if(db == null) {
                return null;
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM transactions WHERE id = ?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        int userId = rs.getInt("user_id");
                        String description = rs.getString("description");
                        String paymentType = rs.getString("payment_type");
                        double amount = rs.getDouble("amount");
                        String qris = rs.getString("qris");
                        String instructions = rs.getString("instructions");
                        String status = rs.getString("status");
                        String transactionApiId = rs.getString("transaction_id");
                        Date date = new Date(rs.getTimestamp("expired_at").getTime());
                        Date createdAt = new Date(rs.getTimestamp("expired_at").getTime());
                        Transactions transaction = new Transactions(id, userId, amount, description, paymentType, status, transactionApiId, date, createdAt);
                        if(qris != null) {
                            transaction.setQris(qris);
                        }
                        JSONArray instruction = new JSONArray(instructions);
                        for(int i = 0; i < instruction.length(); i++) {
                            JSONObject obj = instruction.getJSONObject(i);
                            List<String> steps = new ArrayList<>();
                            JSONArray step = obj.getJSONArray("steps");
                            for(int j = 0; j < step.length(); j++) {
                                steps.add(step.getString(j));
                            }
                            transaction.addPaymentInstructions(obj.getString("title"),steps);
                        }
                        return transaction;
                    }
                    return null;
                } catch (JSONException e) {
                    Utils.debugLog(e.getMessage());
                    return null;
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
            return null;
        }
    }

    public Optional<List<Transactions>> getTransactionsByUserId(int userId) {
        ArrayList<Transactions> transactions = new ArrayList<>();
        //get transactions by user id
        try(Connection db = this.getConn()) {
            if(db == null) {
                return Optional.of(transactions);
            }
            //select query from games table
            try (PreparedStatement ps = db.prepareStatement("SELECT * FROM transactions WHERE user_id = ?")) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        int id = rs.getInt("id");
                        int userIdT = rs.getInt("user_id");
                        String description = rs.getString("description");
                        String paymentType = rs.getString("payment_type");
                        double amount = rs.getDouble("amount");
                        String qris = rs.getString("qris");
                        String status = rs.getString("status");
                        String transactionApiId = rs.getString("transaction_id");
                        Date date = new Date(rs.getTimestamp("expired_at").getTime());
                        Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
                        Transactions transaction = new Transactions(id, userIdT, amount, description, paymentType, status, transactionApiId, date, createdAt);
                        if(qris != null) {
                            transaction.setQris(qris);
                        }
                        transactions.add(transaction);
                    }
                }
            }
        } catch(SQLException err) {
            Utils.debugLog(err.getMessage());
        }
        return Optional.of(transactions);
    }

    public int createTransaction(int amount,
                                 String description,
                                 String paymentType,
                                 String instructions,
                                 String transactionId,
                                 String qris,
                                 String transactionData,
                                 long expiredAt) {
        int idTransaction = -1; // default value if no transaction is created
        try (Connection db = this.getConn()) {
            if (db == null) {
                return idTransaction;
            }
            try (PreparedStatement ps = db.prepareStatement("INSERT INTO transactions (user_id, amount, description, payment_type, instructions, transaction_id, qris, transaction_data, expired_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Main.authenticatedUser.getId());
                ps.setInt(2, amount);
                ps.setString(3, description);
                ps.setString(4, paymentType);
                ps.setString(5, instructions);
                ps.setString(6, transactionId);
                ps.setString(7, qris);
                ps.setString(8, transactionData);
                ps.setTimestamp(9, Timestamp.from(Instant.ofEpochMilli(expiredAt * 1000L)));
                ps.executeUpdate();

                // Retrieve the generated key for the inserted row
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idTransaction = rs.getInt(1);
                }
            }
        } catch (SQLException err) {
            Utils.debugLog(err.getMessage());
        }
        return idTransaction;
    }


    public void updateTransactionStatusById(int transactionId, String status) {
        try (Connection db = this.getConn()) {
            if (db == null) {
                return;
            }
            try (PreparedStatement ps = db.prepareStatement("UPDATE transactions SET status = ? WHERE id = ?")) {
                ps.setString(1, status.toUpperCase());
                ps.setInt(2, transactionId);
                ps.executeUpdate();
            }
        } catch (SQLException err) {
            Utils.debugLog(err.getMessage());
        }
    }
}
