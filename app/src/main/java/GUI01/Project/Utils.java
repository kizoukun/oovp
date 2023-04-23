/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import java.text.NumberFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author rezab
 */
public class Utils {
    
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
    
    public static String formatNumber(int number) {
        return NumberFormat.getInstance().format(number);
    }
    
    public static String formatNumber(double number) {
        return NumberFormat.getInstance().format(number);
    }

    public static void debugLog(String message) {
        if(Main.DEBUG) {
            System.out.println(message);
        }
    }
    
}
