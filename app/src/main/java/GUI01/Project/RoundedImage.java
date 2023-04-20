/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author rezab
 */
public class RoundedImage {
    public static Image getRoundedImage(Image image) {
        // Create a buffered image with a transparent background
        BufferedImage rounded = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        
        // Create a graphics object for the buffered image
        Graphics2D g2d = rounded.createGraphics();
        g2d.setClip(new Ellipse2D.Float(0, 0, image.getWidth(null), image.getHeight(null)));
        g2d.drawImage(image, 0, 0, null);
        
        // Dispose the graphics object
        g2d.dispose();
        
        return rounded;
    }
    
    private static class RoundedButtonUI extends BasicButtonUI {
        @Override
        public void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            b.setBorderPainted(false);
            b.setOpaque(false);
        }
        
        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int x = 0;
            int y = 0;
            int w = c.getWidth();
            int h = c.getHeight();
            int arc = Math.min(w, h);
            
            g2d.setColor(c.getBackground());
            g2d.fillRoundRect(x, y, w, h, arc, arc);
            
            super.paint(g, c);
        }
    }
}
