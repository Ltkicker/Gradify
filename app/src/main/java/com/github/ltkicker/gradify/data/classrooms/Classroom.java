package com.github.ltkicker.gradify.data.classrooms;

import com.github.ltkicker.gradify.data.grades.GradingSystem;
import com.github.ltkicker.gradify.data.users.Student;
import com.github.ltkicker.gradify.data.users.Teacher;

import java.util.List;

public class Classroom {

    private String code;
    private String title;
    private String section;
    private String teacher;
    private List<String> students;
    private List<String> days;
    private List<String> time;
    private String room;
    private String building;

    private GradingSystem gradingSystem;
    //    private int lab;
//    private int lec;
    private int units;


    public Classroom(String code, String section, String title, String room, String building, String teacher) {
        this.code = code;
        this.section = section;
        this.title = title;
        this.room = room;
        this.building = building;
        this.teacher = teacher;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
