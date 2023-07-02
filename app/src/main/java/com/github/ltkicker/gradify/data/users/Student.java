package com.github.ltkicker.gradify.data.users;

import com.github.ltkicker.gradify.data.classrooms.Classroom;

import java.util.List;

public class Student extends User {

    private int yearlevel;
    private String college;
    private String course;
    private String id;
    private List<Classroom> classroom;
    //Private string scholarshipstat;
//Private string scholasticstat;
//Private double prevgpa;


    public Student(String lastname){
    this.lastName = lastname;
    }

    public int getYearlevel() {
        return yearlevel;
    }

    public void setYearlevel(int yearlevel) {
        this.yearlevel = yearlevel;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Classroom> getClassroom() {
        return classroom;
    }

    public void setClassroom(List<Classroom> classroom) {
        this.classroom = classroom;
    }

}
