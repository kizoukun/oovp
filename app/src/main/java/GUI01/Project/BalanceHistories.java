/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import GUI01.Project.Database.Database;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rezab
 */
public class BalanceHistories {

    List<Balance> balances;
    private final int userId;

    public BalanceHistories(int userId, List<Balance> balances) {
        this.userId = userId;
        this.balances = balances;
    }


    public double getBalance() {
        if(this.balances.isEmpty()) {
            return 0;
        }
        Balance balance = this.balances.get(this.balances.size() - 1);
        return balance.getBalance();
    }

    public void addBalance(double amount, String description) {
        double balance_before = this.getBalance();
        double balance_after = balance_before + amount;
        try {
            Database.insertBalanceHistory(this.userId, 1, amount, balance_after, balance_before, description);
        } catch (SQLException e) {
            Utils.debugLog(e.getMessage());
        }
        Balance balance = new Balance(this.userId, amount, balance_after, balance_before, 1, description, new Date());
        this.balances.add(balance);
    }

    public void takeBalance(double amount, String description) {
        double balance_before = this.getBalance();
        double balance_after = balance_before - amount;
        if(balance_after < 0) {
            return;
        }
        try {
            Database.insertBalanceHistory(this.userId, 0, amount, balance_after, balance_before, description);
        } catch (SQLException e) {
            Utils.debugLog(e.getMessage());
        }
        Balance balance = new Balance(this.userId, amount, balance_after, balance_before, 0, description, new Date());
        this.balances.add(balance);
    }

    public List<Balance> getBalances() {
        return balances;
    }
}
