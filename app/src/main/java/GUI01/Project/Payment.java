/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI01.Project;

import GUI01.Project.Api.Tripay;
import GUI01.Project.Object.Transactions;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rezab
 */
public class Payment extends javax.swing.JInternalFrame {

    /**
     * Creates new form Payment
     */
    public Payment(int id) {
        transaction = Main.transactionsDb.getTransactionById(id);
        if(transaction == null) {
            Alert.showMessageError("Transaction not found");
            return;
        }
        initComponents();
        String tutor = "<html>";
        HashMap<String, List<String>> instructions = transaction.getPaymentInstructions();
        for(String keyset : instructions.keySet()) {
            tutor += "<b>" + keyset + "</b><br>";
            int i = 0;
            for(String step : instructions.get(keyset)) {
                i++;
                if(i > instructions.get(keyset).size() - 1) {
                    step += "<br>";
                }
                tutor += step + "<br>";
            }
        }
        tutor += "</html>";
        JLabel test = new JLabel();
        test.setText(tutor);
        payment.setLayout(new BoxLayout(payment, BoxLayout.Y_AXIS));
        if(transaction.getQris() != null) {
            try {
                URL url = new URL(transaction.getQris());
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                ImageIcon imageIcon = new ImageIcon(image);
                Image scaledImage = imageIcon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setOpaque(true);
                payment.add(imageLabel);
            } catch (MalformedURLException e) {
                Utils.debugLog(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String status = String.format("Status: %s<br><br> Description: %s<br><br>Amount: %s<br><br>Expired At: %s", transaction.getStatus(), transaction.getDescription(), Utils.formatNumber(transaction.getAmount()), Utils.formatDate(transaction.getExpiredAt()));
        String htmlShow = String.format("<html><body><b>%s</b></body></html>", status);
        JLabel label = new JLabel(htmlShow);
        payment.add(Box.createRigidArea(new Dimension(0, 10))); // add 10-pixel vertical space
        payment.add(label);
        tutorPayment.setLayout(new BoxLayout(tutorPayment, BoxLayout.Y_AXIS));
        tutorPayment.add(test);
        tutorPayment.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        payment = new javax.swing.JPanel();
        tutorPayment = new javax.swing.JPanel();
        CheckPayment = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Payment");

        javax.swing.GroupLayout paymentLayout = new javax.swing.GroupLayout(payment);
        payment.setLayout(paymentLayout);
        paymentLayout.setHorizontalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );
        paymentLayout.setVerticalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tutorPaymentLayout = new javax.swing.GroupLayout(tutorPayment);
        tutorPayment.setLayout(tutorPaymentLayout);
        tutorPaymentLayout.setHorizontalGroup(
            tutorPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        tutorPaymentLayout.setVerticalGroup(
            tutorPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        CheckPayment.setText("Check Payment");
        CheckPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckPaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(CheckPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(tutorPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tutorPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(CheckPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckPaymentActionPerformed
        checkPayment(transaction.getId());
    }//GEN-LAST:event_CheckPaymentActionPerformed

    private void checkPayment(int id) {
        JSONObject data = null;
        try {
            data = Tripay.getTransaction(transaction.getTransactionApiId());
            System.out.println(data);
        } catch (Exception e) {
            Alert.showMessageError(e.getMessage());
            return;
        }
        if(data == null) {
            Alert.showMessageError("Transaction not found");
            return;
        }

        transaction = Main.transactionsDb.getTransactionById(id);
        if(transaction == null) {
            Alert.showMessageError("Transaction not found");
            return;
        }
        try {
            if(data.getString("status").equalsIgnoreCase("paid")) {
                if(transaction.getStatus().equalsIgnoreCase("paid")) {
                    Alert.showMessageSuccess("You already paid this transaction and get the account(s)!");
                    return;
                }
                try {
                    Main.mainStatic.sendGamesInCart(transaction.getId());
                } catch (Exception e) {
                    Utils.debugLog(e.getMessage());
                }
            }
            transaction.setStatus(data.getString("status"));
        } catch (JSONException e) {
            Utils.debugLog(e.getMessage());
            Alert.showMessageError("Failed to parse transaction data");
        }
        if(!transaction.getStatus().equalsIgnoreCase("unpaid")) {
            Main.transactionsDb.updateTransactionStatusById(transaction.getId(), transaction.getStatus());
        }
        if(transaction.getStatus().equalsIgnoreCase("paid")) {
            Alert.showMessageSuccess("Congratulation your payment is success\nCheck your purchase history menu to get the account(s)!");
        } else {
            Alert.showMessageSuccess("Transaction status: " + transaction.getStatus());
        }
    }

    private Transactions transaction;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckPayment;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel payment;
    private javax.swing.JPanel tutorPayment;
    // End of variables declaration//GEN-END:variables
}