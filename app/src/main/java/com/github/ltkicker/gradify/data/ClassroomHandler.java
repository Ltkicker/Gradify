package com.github.ltkicker.gradify.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassroomHandler {
    private static final DatabaseReference dbClass = FirebaseDatabase.getInstance().getReference("classrooms");

    public static void createClassroom(String className, String instructorName) {
        String classroomId = dbClass.push().getKey();
        Classroom classroom = new Classroom(classroomId, className, instructorName);
        assert classroomId != null;
        dbClass.child(classroomId).setValue(classroom);
    }

    public static void getClassroom(String classroomId) {
    }
}