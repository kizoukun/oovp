/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rezab
 */
class JCheckBoxExample {
    JCheckBoxExample(){  
        JFrame f= new JFrame("CheckBox Example");  
		
        JCheckBox checkBox1 = new JCheckBox("C++");  
        checkBox1.setBounds(100,100, 50,50);  
        f.add(checkBox1);  
        
		JCheckBox checkBox2 = new JCheckBox("Java", true);  
        checkBox2.setBounds(100,150, 50,50);  
        f.add(checkBox2);  
		
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);  
    }  
}

class JColorChooserExample extends JFrame implements ActionListener {
    JButton b;
    Container c;
    JColorChooserExample(){
            c = getContentPane();    
            c.setLayout(new FlowLayout());         
            b = new JButton("color");    
            b.addActionListener(this);         
            c.add(b);    
            this.setSize(400, 400);
            this.setVisible(true);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }    
    public void actionPerformed(ActionEvent e) {    
            Color initialcolor=Color.RED;    
            Color color = JColorChooser.showDialog(this,"Select a color",initialcolor);    
            c.setBackground(color);    
    }  
}

public class withoutFrame {
    public static void main(String[] args) {
        new JColorChooserExample();    
    }
}
