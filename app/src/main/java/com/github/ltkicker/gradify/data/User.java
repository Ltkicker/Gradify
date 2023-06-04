package com.github.ltkicker.gradify.data;

public class User {
    public String lastName;
    public String firstName;
    public String middleName;
    public String suffixName;
    public String email;
    public String username;

    public User(String lastName, String firstName, String middleName, String suffixName, String email, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffixName = suffixName;
        this.email = email;
        this.username = username;
    }
}
