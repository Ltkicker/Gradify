package com.github.ltkicker.gradify.data.grades.Category;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.grades.Quiz;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class QuizCategory extends Quiz {
    private List<Quiz> quiz = new ArrayList<>();

    private DatabaseReference gsDRef = FirebaseDatabase.getInstance().getReference("grades");
    public QuizCategory(String title, double score) {
        super(title, score);
    }

    public void addCategory(String plural) {
        addCategory(plural, plural);
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> QuizCategory = new ArrayList<>();
        gsDRef.child("categories").child("grades").child("project").addListenerForSingleValueEvent(new ValueEventListener() {
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
