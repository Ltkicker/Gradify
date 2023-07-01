package com.github.ltkicker.gradify.calculator;

import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private Classroom classroom;
    private List<Student> student;
    private String username;

    public Leaderboard(Classroom classroom){
     this.classroom = classroom;
    }

    private void getClasscode(){
        classroom.getCode();
    }

    public void Student(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }


    public static void sortStudentsByGrade(List<Student> students) {
//        Collections.sort(students, new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                return Integer.compare(s2.getGrade(), s1.getGrade()); // Descending order
//            }
//        });
    }
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public void createClass() {
        reference.child("classrooms").child("teacher").setValue("Admin");
    }

}


