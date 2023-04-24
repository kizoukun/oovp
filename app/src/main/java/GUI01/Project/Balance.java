/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import java.util.Date;

/**
 *
 * @author rezab
 */
public class Balance {

    private int id;
    private int type;
    private double amount;
    private double balance_after;
    private double balance_before;
    private String description;
    private Date date;

    public Balance(int id, double amount, double balance_after, double balance_before, int type, String description, Date date) {
        this.id = id;
        this.amount = amount;
        this.balance_after = balance_after;
        this.balance_before = balance_before;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public double getBalance() {
        return this.balance_after;
    }

    public Date getDate() {
        return this.date;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getType() {
        return this.type;
    }

    public int getId() {
        return this.id;
    }

    public double getBalanceAfter() {
        return this.balance_after;
    }

    public double getBalanceBefore() {
        return this.balance_before;
    }

    public String getDescription() {
        return this.description;
    }

}
