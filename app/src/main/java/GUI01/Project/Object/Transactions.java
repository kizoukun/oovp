package GUI01.Project.Object;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Transactions {

    private final int id;
    private final int userId;
    private final double amount;
    private final String description;
    private final String paymentType;
    private String status;
    private final String transactionApiId;
    private final Date expiredAt;
    private final Date createdAt;
    private String qris;
    private HashMap<String, List<String>> paymentInstructions = new HashMap<>();


    public Transactions(int id, int userId, double amount, String description, String paymentType, String status, String transactionApiId, Date expiredAt, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.paymentType = paymentType;
        this.status = status;
        this.transactionApiId = transactionApiId;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
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

    public void setStatus(String status) {
        this.status = status;
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

    public Date getExpiredAt() {
        return this.expiredAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getTransactionApiId() {
        return this.transactionApiId;
    }


}
