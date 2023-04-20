/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

/**
 *
 * @author rezab
 */
public class GameObject {
    private double price;
    private String name;
    private String image = null;
    
    GameObject(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    GameObject(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return this.image;
    }
}
