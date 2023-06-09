package com.github.ltkicker.gradify.data.classrooms;

import com.github.ltkicker.gradify.data.users.Student;
import com.github.ltkicker.gradify.data.users.Teacher;

import java.util.List;

public class Classroom {

    private String code;
    private String title;
    private String section;
    private Teacher teacher;
    private List<Student> students;
    private List<String> days;
    private List<String> time;
    private List<String> room;
    //    private int lab;
//    private int lec;
    private int units;


    public Classroom() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }


    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
