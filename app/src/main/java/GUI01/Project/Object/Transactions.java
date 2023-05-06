package GUI01.Project.Object;

import java.util.HashMap;
import java.util.List;

public class Transactions {

    private final int id;
    private final int userId;
    private final double amount;
    private final String description;
    private final String paymentType;
    private final String status;
    private String qris;
    private HashMap<String, List<String>> paymentInstructions = new HashMap<>();


    public Transactions(int id, int userId, double amount, String description, String paymentType, String status) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.paymentType = paymentType;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public String getStatus() {
        return this.status;
    }

    public String getQris() {
        return this.qris;
    }

    public void setQris(String qris) {
        this.qris = qris;
    }

    public HashMap<String, List<String>> getPaymentInstructions() {
        return paymentInstructions;
    }

    public void addPaymentInstructions(String title, List<String> instructions) {
        paymentInstructions.put(title, instructions);
    }

    public void clearPaymentInstructions() {
        paymentInstructions.clear();
    }


}
