package com.github.ltkicker.gradify.data.users;

import com.github.ltkicker.gradify.data.classrooms.Classroom;

import java.util.List;

public class Teacher extends User {

    //    private String course;
//    private String department;
//    private String levelofwork; // assistant prof, prof, lecturer, instructor
//    private String id;
    public Teacher() {
    }

    private List <Classroom> classroom;

    public List<Classroom> getClassroom() {
        return classroom;
    }

    public void setClassroom(List<Classroom> classroom) {
        this.classroom = classroom;
    }

}
