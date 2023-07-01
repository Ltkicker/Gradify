package com.github.ltkicker.gradify.data.classrooms;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassroomHandler {

    private DatabaseReference uDataRef;
    private DatabaseReference cDataRef;

    public ClassroomHandler() {
        uDataRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void getClasses(String username, boolean isTeacher, FirebaseUtils.ClassListListener listener) {
        getClassesById(username, isTeacher, new FirebaseUtils.ClassListByIdListener() {
            @Override
            public void onUpdate(ArrayList<String> classListById) {

            }
        });
    }

    private void getClassesById(String username, boolean isTeacher, FirebaseUtils.ClassListByIdListener listener) {
        String path = getUserPath(username, isTeacher);
        uDataRef.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<String> list = new ArrayList<>();
                    for (DataSnapshot value : snapshot.getChildren()) {
                        String classroom = value.getValue(String.class);
                        list.add(classroom);
                    }
                    listener.onUpdate(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String getUserPath(String username, boolean isTeacher) {
        if(isTeacher) {
            return username + "/classrooms/asTeacher";
        } else {
            return username + "/classrooms/asStudent";
        }
    }
}