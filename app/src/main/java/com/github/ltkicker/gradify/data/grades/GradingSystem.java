package com.github.ltkicker.gradify.data.grades;

import com.github.ltkicker.gradify.calculator.GradeCategory;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GradingSystem {

    Classroom classroom;
    List<GradeCategory> categories;

    public GradingSystem(Classroom classroom, List<GradeCategory> categories){
        this.classroom = classroom;
        this.categories = categories;
    }
}
