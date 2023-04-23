/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import java.util.Date;

/**
 *
 * @author rezab
 */
public class GameObject {
    private double price;
    private String title;
    private String image = null;
    private Date addedDate;
    
    public GameObject(String title, double price) {
        this.title = title;
        this.price = price;
    }
    
    public GameObject(String title, double price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public GameObject(String title, double price, String image, Date date) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.addedDate = date;
    }

    public GameObject(String title, double price, Date date) {
        this.title = title;
        this.price = price;
        this.addedDate = date;
    }
    
    public String getTitle() {
        return this.title;
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

    public Date getAddedDate() {
        return this.addedDate;
    }
}
