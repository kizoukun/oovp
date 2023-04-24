/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI01.Project.Dashboard;

import GUI01.Project.Alert;
import GUI01.Project.Database.UsersDatabase;
import GUI01.Project.Main;
import GUI01.Project.User;
import GUI01.Project.Utils;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author rezab
 */
public class Profile extends javax.swing.JInternalFrame {

    /**
     * Creates new form Profile
     */
    public Profile() {
        initComponents();
        this.emailText.setText(Main.authenticatedUser.getEmail());
        this.fullNameText.setText(Main.authenticatedUser.getFullName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fullNameText = new javax.swing.JTextField();
        editNameBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordEditBtn = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setText("Email");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("PROFILE");

        emailText.setEditable(false);

        jLabel3.setText("Full Name");

        fullNameText.setEditable(false);

        editNameBtn.setText("Edit");
        editNameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNameBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Password");

        passwordField.setText("jPasswordField1");

        passwordEditBtn.setText("Edit");
        passwordEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordEditBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField)
                            .addComponent(fullNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(emailText))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editNameBtn)
                    .addComponent(passwordEditBtn))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fullNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editNameBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordEditBtn))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editNameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNameBtnActionPerformed
        User user = Main.authenticatedUser;
        String firstName = JOptionPane.showInputDialog("Enter your new First Name", user.getFirstName());
        if(firstName == null) return;
        if(firstName.length() < 1) {
            Alert.showMessageError(this, "First Name cannot be empty");
            return;
        }
        String lastName = JOptionPane.showInputDialog("Enter your new Last Name", user.getLastName());
        if(lastName == null) return;
        if(lastName.length() < 1) {
            Alert.showMessageError(this, "Last Name cannot be empty");
            return;
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        this.fullNameText.setText(user.getFullName());
        Main.usersDb.updateFullName(user.getFirstName(), user.getLastName());
        Alert.showMessageSuccess(this, "Your new full name is: " + user.getFullName());
    }//GEN-LAST:event_editNameBtnActionPerformed

    private void passwordEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordEditBtnActionPerformed
        User user = Main.authenticatedUser;
        JPasswordField currentPasswordField = new JPasswordField();
        int resultCp = JOptionPane.showConfirmDialog(null, currentPasswordField, "Enter your current password", JOptionPane.OK_CANCEL_OPTION);
        if (resultCp != JOptionPane.OK_OPTION) return;
        String currentPassword = new String(currentPasswordField.getPassword());
        if(!Utils.checkPassword(currentPassword, user.getHashedPassword())){
            Alert.showMessageError(this, "Incorrect password");
            return;
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel label1 = new JLabel("New Password:");
        JPasswordField input1 = new JPasswordField();
        panel.add(label1);
        panel.add(input1);

        JLabel label2 = new JLabel("Confirm Password:");
        JPasswordField input2 = new JPasswordField();
        panel.add(label2);
        panel.add(input2);

        int result = JOptionPane.showConfirmDialog(null, panel, "New Password", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;
        String newPassword = new String(input1.getPassword());
        String confirmPassword = new String(input2.getPassword());
        if(!newPassword.equals(confirmPassword)) {
            Alert.showMessageError(this, "Your password is not the same");
        }
        user.setHashedPassword(Utils.encryptPassword(newPassword));
        Alert.showMessageSuccess(this, "Successfully changed your password");
    }//GEN-LAST:event_passwordEditBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editNameBtn;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField fullNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton passwordEditBtn;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration//GEN-END:variables
}
