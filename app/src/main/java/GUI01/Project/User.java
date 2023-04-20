/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI01.Project;

/**
 *
 * @author rezab
 */
public class User {
    
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String hashedPassword;
    
    public User(String email) {
        this.email = email;
    }
    
    public User(String email, String firstName, String lastName, String gender, String hashedPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hashedPassword = hashedPassword;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
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
}
