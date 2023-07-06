package com.github.ltkicker.gradify.data.grades;

import android.util.Log;

import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;


import java.util.HashMap;
import java.util.Map;

public class UserStandingData {
    String studentId;
    String classroomId;
    double finalGrade;
    String finalGradeFormatted;



    HashMap<ParentCategory, Double> breakdown = new HashMap<>();

    public UserStandingData(String studentId, String classroomId) {
        this.classroomId = classroomId;
        this.studentId = studentId;
    }

    public void addCategoryScore(ParentCategory category, double score) {
        breakdown.put(category, score);
        double a = 0.0;
        for (ParentCategory parent : breakdown.keySet()) {
            Double value = breakdown.get(parent);
            a += value;
        }
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public HashMap<ParentCategory, Double> getBreakdown() {
        return breakdown;
    }


//    public void calculatePersonalGrade(String classroomId) {
//        FirebaseDatabase.getInstance().getReference("grades").child(classroomId)
//                .child("students").child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(Da)
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }

    public void printToLogcat() {
        Log.d("UserStandingData", " ");
        Log.d("UserStandingData", "From classroom: " + classroomId);
        Log.d("UserStandingData", "Student issued: " + studentId);
        Log.d("UserStandingData", "Grade Breakdown:");
        for (Map.Entry<ParentCategory, Double> entry : breakdown.entrySet()) {
            String key = entry.getKey().getName();
            Double value = entry.getValue();
            Log.d("UserStandingData", "  - " + key + ": " + value);
        }
        Log.d("UserStandingData", " ");
    }

}
