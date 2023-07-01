package com.github.ltkicker.gradify.data.classrooms;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.grades.GradingInstanceData;
import com.github.ltkicker.gradify.data.grades.GradingSystem;
import com.github.ltkicker.gradify.data.users.Student;
import com.github.ltkicker.gradify.data.users.Teacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Classroom implements Serializable {

    private String code;
    private String title;
    private String section;
    private String teacher;
    private List<String> students;
    private List<String> days;
    private List<String> time;
    private String room;
    private String building;
    private int units;

    public Classroom() {

    }

    public Classroom(String code, String section, String title, String room, String building, String teacher, String key) {
        this.code = code;
        this.section = section;
        this.title = title;
        this.room = room;
        this.building = building;
        this.teacher = teacher;

        //FirebaseDatabase.getInstance().getReference("grades").child(key).setValue(new GradingSystem());
    }

    public void getData(Classroom classroom) {
        this.code = classroom.code;
        this.title = classroom.title;
        this.section = classroom.section;
        this.teacher = classroom.teacher;
        this.students = classroom.students;
        this.days = classroom.days;
        this.time = classroom.time;
        this.room = classroom.room;
        this.building = classroom.building;
        this.units = classroom.units;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

}
