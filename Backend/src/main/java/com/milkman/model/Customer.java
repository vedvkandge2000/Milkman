package com.milkman.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Customer {

    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id") // This column is both PK and FK to User
    private User user;

    private String name;

    @Column(unique = true)
    private String phoneNumber;
    private String email;
    private String address;
    private int familySize;
    private double defaultMilkQty;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public double getDefaultMilkQty() {
        return defaultMilkQty;
    }

    public void setDefaultMilkQty(double defaultMilkQty) {
        this.defaultMilkQty = defaultMilkQty;
    }
}