package com.github.ltkicker.gradify.calculator;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.github.ltkicker.gradify.data.users.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardCalc {
    private String classKey;

    private void startCalculate(Classroom classroom) {
//        ArrayList<Student> students = classroom.getStudents();

//        FirebaseDatabase.getInstance().getReference("grading").child("classroom").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot childSnapshot : snapshot.getChildren()) {
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}
