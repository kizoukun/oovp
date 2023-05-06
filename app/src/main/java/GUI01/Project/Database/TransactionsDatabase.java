package GUI01.Project.Database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import GUI01.Project.Object.Transactions;
import GUI01.Project.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
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
                        String status = rs.getString("status");
                        Transactions transaction = new Transactions(id, userId, amount, description, paymentType, status);
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
                        Transactions transaction = new Transactions(id, userId, amount, description, paymentType, status);
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

    public void createTransaction(int amount, String email, String description, String paymentType) {

    }

}
