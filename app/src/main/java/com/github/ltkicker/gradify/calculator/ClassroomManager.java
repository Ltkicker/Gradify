package com.github.ltkicker.gradify.calculator;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClassroomManager {
    private DatabaseReference classDRef = FirebaseDatabase.getInstance().getReference("classrooms");
    private Classroom classroom;

    private String classroomId;
    HashMap<String, String> gradeCategories;

    public ClassroomManager(String classroomId) {
        this.classroomId = classroomId;
    }


}
