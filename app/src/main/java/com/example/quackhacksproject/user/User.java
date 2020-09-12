package com.example.quackhacksproject.user;

public class User {
    public String  email,  firstName, lastName, position;
    public User(){};

    public User( String email, String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;

    }
}
