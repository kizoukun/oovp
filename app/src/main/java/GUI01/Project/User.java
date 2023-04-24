/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rezab
 */
public class User extends BalanceHistories {

    private final int id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String hashedPassword;
    private List<GameObject> games;
    
    public User(int id, String email, String firstName, String lastName, String gender, String hashedPassword, List<Balance> balances) {
        super(id, balances);
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hashedPassword = hashedPassword;
        this.games = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
       this.lastName = lastName;
    }
    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setGames(List<GameObject> games) {
        this.games = games;
    }

    public void addGames(GameObject game) {
        this.games.add(game);
    }

    public List<GameObject> getGames() {
        return this.games;
    }

    @Override
    public String toString() {
        return getGender().equalsIgnoreCase("m") ? "Mr. " + getFullName() : "Ms. " + getFullName();
    }
}
