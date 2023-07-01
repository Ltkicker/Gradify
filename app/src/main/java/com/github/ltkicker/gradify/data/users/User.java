package com.github.ltkicker.gradify.data.users;

import com.github.ltkicker.gradify.data.classrooms.Classroom;

import java.util.ArrayList;

public class User {

    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String username;
    private String gender;
    private String idNumber;

    private ArrayList<Classroom> classrooms;

    private boolean isTeacher;

    private boolean hasClassrooms;


    public User() {
    }

    public User(boolean isTeacher, String lastName, String firstName, String middleName, String idNumber, String email, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.username = username;
        this.isTeacher = isTeacher;
        //this.gender = gender;
        this.hasClassrooms = false;
        this.idNumber = idNumber;
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

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName + " ";
    }

    public String getIdNumber() {
        return idNumber;
    }

}
