package entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private String adress;
    private int phoneNumber;

    // Constructor
    public Customer(String name, String email, String adress, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // toString method Override
    public String toString() {
        return "Name: " + name + " | " +
                "E-mail: " + email + " | " +
                "Adress: " + adress + " | " +
                "Phone: " + phoneNumber;
    }
}
