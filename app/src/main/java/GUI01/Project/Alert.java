/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author rezab
 */
public class Alert {
    
    public static void showMessageError(Component parentComponent, String msg) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showMessageSuccess(Component parentComponent, String msg) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Success", JOptionPane.PLAIN_MESSAGE);
    }
}
