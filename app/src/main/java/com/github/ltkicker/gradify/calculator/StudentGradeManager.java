package com.github.ltkicker.gradify.calculator;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.activities.authentication.SignupActivity;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.github.ltkicker.gradify.data.grades.StudentGrade;
import com.github.ltkicker.gradify.data.grades.UserStandingData;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentGradeManager {
    private static DatabaseReference dRef;
    private static String classroomId;

    public void init() {

    }

    public static void startCalculate() {
        classroomId = "NZItQ2M6m_y9IXcgOW7";
        dRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId);
        dRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    // firstSnapshot: studentId
                    // secondSnapshot: categories
                    // thirdSnapshot: subcategories
                    for(DataSnapshot firstSnapshot : snapshot.getChildren()) {
                        UserStandingData userStanding = new UserStandingData(firstSnapshot.getKey(), classroomId);
                        for(DataSnapshot secondSnapshot : firstSnapshot.getChildren()) {
                            String categoryStr = secondSnapshot.getKey();
                            ParentCategory category = new ParentCategory(categoryStr, 0.1);
                            double categoryGrade = 0;
                            for(DataSnapshot thirdSnapshot : secondSnapshot.getChildren()) {
                                categoryGrade += thirdSnapshot.getValue(Integer.class);
                            }
                            userStanding.addCategoryScore(category, categoryGrade);
                        }
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
