/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI01.Project;

import GUI01.Project.Authentication.Login;
import GUI01.Project.Authentication.Register;
import GUI01.Project.Dashboard.Profile;
import GUI01.Project.Dashboard.UserBalanceHistories;
import GUI01.Project.Dashboard.UserGames;
import GUI01.Project.Database.Database;
import GUI01.Project.Database.GamesDatabase;
import GUI01.Project.Database.UsersDatabase;
import GUI01.Project.Object.UserGamesObject;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.SwingConstants;

/**
 *
 * @author Yudhistira Fauzy
 */


public class Main extends javax.swing.JFrame {

    public static User authenticatedUser = null;
    public static boolean DEBUG = true;
    public static UsersDatabase usersDb;
    public static GamesDatabase gamesDb;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        usersDb.updatePassword(Utils.encryptPassword("12345678"), 3);
        this.loadGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JPanel();
        checkoutBtn = new javax.swing.JButton();
        listGamePane = new javax.swing.JPanel();
        purchasedGamePane = new javax.swing.JPanel();
        paymentBox = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        myGamesItem = new javax.swing.JMenuItem();
        balanceHistoryItem = new javax.swing.JMenuItem();
        profileMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        loginForm = new javax.swing.JMenuItem();
        registerMenu = new javax.swing.JMenuItem();
        logoutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        checkoutBtn.setText("Checkout");
        checkoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listGamePaneLayout = new javax.swing.GroupLayout(listGamePane);
        listGamePane.setLayout(listGamePaneLayout);
        listGamePaneLayout.setHorizontalGroup(
            listGamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );
        listGamePaneLayout.setVerticalGroup(
            listGamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        purchasedGamePane.setLayout(new java.awt.GridLayout(1, 0));

        paymentBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Balance", "QRIS", "BCA" }));
        paymentBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(listGamePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(paymentBox, 0, 116, Short.MAX_VALUE)
                    .addComponent(checkoutBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(purchasedGamePane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(purchasedGamePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(listGamePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(checkoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        jMenu1.setText("Menu");

        myGamesItem.setText("My Games");
        myGamesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myGamesItemActionPerformed(evt);
            }
        });
        jMenu1.add(myGamesItem);

        balanceHistoryItem.setText("Balance Histories");
        balanceHistoryItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceHistoryItemActionPerformed(evt);
            }
        });
        jMenu1.add(balanceHistoryItem);

        profileMenuItem.setText("Profile");
        profileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(profileMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Auth");

        loginForm.setText("Login");
        loginForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginFormActionPerformed(evt);
            }
        });
        jMenu2.add(loginForm);

        registerMenu.setText("Register");
        registerMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerMenuActionPerformed(evt);
            }
        });
        jMenu2.add(registerMenu);

        logoutMenu.setText("Logout");
        logoutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuActionPerformed(evt);
            }
        });
        jMenu2.add(logoutMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutBtnActionPerformed
        if(authenticatedUser == null) {
            Alert.showMessageError(this, "Please login first to purchase!");
            return;
        }
        if(checkOutList.size() < 1) {
            Alert.showMessageError(this, "Please add game to cart first!");
            return;
        }



        double totalPrice = 0;
        for(GameObject checkout : checkOutList) {
            totalPrice += checkout.getPrice();
        }

        String paymentType = Objects.requireNonNull(paymentBox.getSelectedItem()).toString();
        double money = 0;

        if(paymentType.equalsIgnoreCase("cash")) {
            Optional<String> moneyInput = Optional.ofNullable(JOptionPane.showInputDialog(this, "Place your money here", "Cash", JOptionPane.INFORMATION_MESSAGE));
            String moneyString = moneyInput.filter(s -> s.length() > 0).orElse("0");
            money = Double.parseDouble(moneyString);
        } else if(paymentType.equalsIgnoreCase("balance")) {
            money = authenticatedUser.getBalance();
        } else {
            Alert.showMessageError(this, "Selected payment is not available at this time! sorry :(");
            return;
        }
        if(money < totalPrice) {
            Alert.showMessageError(this, "Your money is not enough!");
            return;
        }
        StringBuffer gamesTitles = new StringBuffer();
        int i = 0;
        for(GameObject game : checkOutList) {
            try {
                gamesDb.addOwnedGame(authenticatedUser.getId(), game.getId());
                if(i >= checkOutList.size() - 1) {
                    gamesTitles.append(game.getTitle());
                } else {
                    gamesTitles.append(game.getTitle() + ", ");
                }
                authenticatedUser.addGames(new UserGamesObject(game));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            i++;
        };

        checkOutList.clear();
        this.reloadBuyGame();
        if(paymentType.equalsIgnoreCase("balance")) {
            authenticatedUser.takeBalance(totalPrice, "Purchasing games " + gamesTitles);
            Alert.showMessageSuccess(this, "Congratulation! " + authenticatedUser + "\nYou have successfully purchased the game!\n\n Your balance is now Rp. " + Utils.formatNumber(authenticatedUser.getBalance()));
        } else {
            double moneyLeft = money - totalPrice;
            if(moneyLeft > 0) {
                authenticatedUser.addBalance(moneyLeft, "Change from purchasing games " + gamesTitles);
            }
            authenticatedUser.addExperienceByMoney(totalPrice);
            Alert.showMessageSuccess(this, "Congratulation! " + authenticatedUser + "\nYou have successfully purchased the game!\n\n Your change is Rp. " + Utils.formatNumber(moneyLeft) + "\nChange will be automatically added to balance");
        }
    }//GEN-LAST:event_checkoutBtnActionPerformed

    private void loginFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginFormActionPerformed
        if(authenticatedUser != null) {
            Alert.showMessageError(this, "You are already logged in!");
            return;
        }
        Login login = new Login();
        JLayeredPane pane = getLayeredPane();
        pane.add(login, Integer.valueOf(50));
        login.setVisible(true);
    }//GEN-LAST:event_loginFormActionPerformed

    private void logoutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuActionPerformed
        if(authenticatedUser != null) {
            int answer = JOptionPane.showConfirmDialog(this, "Are you sure to logout?", "Logout", JOptionPane.OK_CANCEL_OPTION);
            if(answer != JOptionPane.YES_OPTION) {
                return;
            }
            Main.authenticatedUser = null;
            Alert.showMessageSuccess(this, "Successfully logged out");
        } else {
            Alert.showMessageError(this, "You are not logged in");
        }
    }//GEN-LAST:event_logoutMenuActionPerformed

    private void registerMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerMenuActionPerformed
        Register register = new Register();
        JLayeredPane pane = getLayeredPane();
        pane.add(register);
        register.setVisible(true);
    }//GEN-LAST:event_registerMenuActionPerformed

    private void paymentBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentBoxActionPerformed
    }//GEN-LAST:event_paymentBoxActionPerformed

    private void myGamesItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myGamesItemActionPerformed
        if(authenticatedUser == null) {
            Alert.showMessageError(this, "You have to login to show games");
            return;
        }
        UserGames userGames = new UserGames();
        JLayeredPane pane = getLayeredPane();
        pane.add(userGames);
        userGames.setVisible(true);
    }//GEN-LAST:event_myGamesItemActionPerformed

    private void balanceHistoryItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceHistoryItemActionPerformed
        if(authenticatedUser == null) {
            Alert.showMessageError(this, "You have to login to show balance histories");
            return;
        }
        UserBalanceHistories ubl = new UserBalanceHistories();
        JLayeredPane pane = getLayeredPane();
        pane.add(ubl);
        ubl.setVisible(true);
    }//GEN-LAST:event_balanceHistoryItemActionPerformed

    private void profileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileMenuItemActionPerformed
        if(authenticatedUser == null) {
            Alert.showMessageError(this, "You have to login to show profile");
            return;
        }
        Profile profile = new Profile();
        JLayeredPane pane = getLayeredPane();
        pane.add(profile);
        profile.setVisible(true);
    }//GEN-LAST:event_profileMenuItemActionPerformed

    public void loadGame() {
        listGamePane.setLayout(new GridLayout(0, 3, 3, 3));
        Optional<List<GameObject>> fetchGames = gamesDb.getGames();
        List<GameObject> games = new ArrayList<>();
        if(fetchGames.isPresent()) {
            games = fetchGames.get();
        }
        for(GameObject game : games) {
            JButton gameBtn = new JButton();
            gameBtn.setLayout(new BoxLayout(gameBtn, BoxLayout.Y_AXIS));
            gameBtn.add(Box.createVerticalGlue());
            if(game.getImage() != null) {
                Image img = new ImageIcon(game.getImage()).getImage();
                Image scaledImg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                gameBtn.add(new JLabel(new ImageIcon(scaledImg)));
            }
            gameBtn.addActionListener((ActionEvent e) -> {
                this.addGame(game);
            });
            gameBtn.add(new JLabel(game.getTitle(), SwingConstants.CENTER));
            gameBtn.add(new JLabel("Rp. " + Utils.formatNumber(game.getPrice()), SwingConstants.CENTER));
            listGamePane.add(gameBtn);
        }
    }
    
    public void addGame(GameObject game) {
        if(authenticatedUser == null) {
            Alert.showMessageError(this, "You have to login to add cart");
            return;
        }
        boolean isGameOwned = authenticatedUser.getGames().stream().anyMatch(uGame -> uGame.getId() == game.getId());
        if(isGameOwned) {
            Alert.showMessageError(this, "You already owned this game!");
            return;
        }
        if(!checkOutList.contains(game)) {
            checkOutList.add(game);
        } else {
            Alert.showMessageError(this, "You already have this game added");
        }
        this.reloadBuyGame();
    }
    
    public void removeGame(GameObject game) {
        checkOutList.remove(game);
        this.reloadBuyGame();
    }
    
    public void reloadBuyGame() {
        purchasedGamePane.removeAll();
        purchasedGamePane.setLayout(new GridLayout(5, 0, 5, 0));
        for(GameObject s : checkOutList) {
            JButton btn = new JButton();
            btn.addActionListener((ActionEvent e) -> {
                this.removeGame(s);
            });
            btn.setLayout(new BoxLayout(btn, BoxLayout.Y_AXIS));
            btn.add(Box.createVerticalGlue());
            btn.add(new JLabel(s.getTitle()));
            
            btn.add(new JLabel("Rp. " + Utils.formatNumber(s.getPrice())));
            purchasedGamePane.add(btn);
        }
        purchasedGamePane.revalidate();
        purchasedGamePane.repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        usersDb = new UsersDatabase();
        gamesDb = new GamesDatabase();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    private final ArrayList<GameObject> checkOutList = new ArrayList<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem balanceHistoryItem;
    private javax.swing.JButton checkoutBtn;
    private javax.swing.JPanel desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel listGamePane;
    private javax.swing.JMenuItem loginForm;
    private javax.swing.JMenuItem logoutMenu;
    private javax.swing.JMenuItem myGamesItem;
    private javax.swing.JComboBox<String> paymentBox;
    private javax.swing.JMenuItem profileMenuItem;
    private javax.swing.JPanel purchasedGamePane;
    private javax.swing.JMenuItem registerMenu;
    // End of variables declaration//GEN-END:variables
}
