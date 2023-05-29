package com.github.ltkicker.gradify.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Classroom {

    private String classroomId;
    private String className;
    private String instructorName;


    public Classroom(String classroomId, String className, String instructorName) {
        this.classroomId = classroomId;
        this.className = className;
        this.instructorName = instructorName;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}
