/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

import GUI01.Project.Object.UserGamesObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rezab
 */
public class User extends BalanceHistories {

    private final int id;
    private int experience;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String hashedPassword;
    private String role;
    private List<UserGamesObject> games;
    
    public User(int id, String email, String firstName, String lastName, String gender, String hashedPassword, List<Balance> balances, int experience) {
        super(id, balances);
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hashedPassword = hashedPassword;
        this.games = new ArrayList<>();
        this.experience = experience;
        this.role = null;
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

    public int getExperience() {
        return this.experience;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role = role;
    }

    public boolean isAdmin() {
        return this.role.equalsIgnoreCase("admin");
    }

    public int getProgressExperience() {
        return this.experience - (this.getLevel() * 100);
    }

    public int getLevel() {
        return (int) Math.floor(this.experience / 100);
    }

    public void addExperience(int experience) {
        int newExperience = this.experience + experience;
        this.experience = newExperience;
        Main.usersDb.setUserExperience(this.getId(), newExperience);
    }

    public void addExperienceByMoney(double money) {
        int experience = (int) Math.floor(money / 1000);
        this.addExperience(experience);
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        Main.usersDb.updatePassword(hashedPassword);
    }

    public void setGames(List<UserGamesObject> games) {
        this.games = games;
    }

    public void addGames(UserGamesObject game) {
        this.games.add(game);
    }

    public List<UserGamesObject> getGames() {
        return this.games;
    }

    @Override
    public String toString() {
        return getGender().equalsIgnoreCase("m") ? "Mr. " + getFullName() : "Ms. " + getFullName();
    }
}
