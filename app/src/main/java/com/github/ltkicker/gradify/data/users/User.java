package com.github.ltkicker.gradify.data.users;

import com.github.ltkicker.gradify.data.classrooms.Classroom;

import java.util.ArrayList;

public class User {

    protected String lastName;
    private String firstName;
    private String middleName;
    private String suffixName;
    private String email;
    private String username;
    private String gender;

    private ArrayList<Classroom> classrooms;

    private boolean isTeacher;

    private boolean hasClassrooms;


    public User() {
    }

    public User(boolean isTeacher, String lastName, String firstName, String middleName, String suffixName, String email, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffixName = suffixName;
        this.email = email;
        this.username = username;
        this.isTeacher = isTeacher;
        //this.gender = gender;
        this.hasClassrooms = false;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

}
