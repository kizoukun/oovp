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
    private final int id;
    private String title;
    private String image = null;
    private Date addedDate;
    private int cartId;
    
    public GameObject(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    
    public GameObject(int id, String title, double price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public GameObject(int id, String title, double price, String image, Date date) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.addedDate = date;
    }

    public GameObject(int id, String title, double price, Date date) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.addedDate = date;
    }

    public int getId() {
        return this.id;
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

    public void setCartId(int cartId){
        this.cartId = cartId;
    }
    public int getCartId() {
        return this.cartId;
    }
}
