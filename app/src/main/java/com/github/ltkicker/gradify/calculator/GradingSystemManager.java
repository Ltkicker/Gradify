package com.github.ltkicker.gradify.calculator;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class GradingSystemManager {
    private String classroomId;
    private DatabaseReference gsDRef = FirebaseDatabase.getInstance().getReference("grades");

    public GradingSystemManager() {}

    public GradingSystemManager(String classroomId) {
        this.classroomId = classroomId;
    }

    public void addCategory(String plural, String singular) {
        gsDRef.child(classroomId).child("categories").child(plural).setValue(singular);
    }

    public void addCategory(String plural) {
        addCategory(plural, plural);
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> allCategories = new ArrayList<>();
        gsDRef.child(classroomId).child("categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        Log.d("Property", "child: " + child.getKey());
                        synchronized (allCategories) {
                            allCategories.add(child.getKey());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Property", error.toString());
            }
        });
        synchronized (allCategories) {
            Log.d("Property", "all " + allCategories);
            return allCategories;
        }
    }

    public boolean updateScore(String category, String instance, String student, double score) {
        AtomicBoolean isPathValid = new AtomicBoolean(false);
        gsDRef.child(classroomId).child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    if(snapshot.child(instance).exists()) {
                        isPathValid.set(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        if (!isPathValid.get()) {
            return false;
        }
        AtomicBoolean containsStudent = new AtomicBoolean(false);
        DatabaseReference checkedStudent = FirebaseDatabase.getInstance().getReference("users").child(student);
        checkedStudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    containsStudent.set(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        if(!containsStudent.get()) {
            return false;
        }
        gsDRef.child(classroomId).child("history").child(category).child(instance).child(student).setValue(score);
        return true;
    }
}
