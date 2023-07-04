package com.github.ltkicker.gradify.data.grades;

import android.util.Log;

import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;

import org.apache.poi.poifs.property.Parent;

import java.util.HashMap;
import java.util.Map;

public class UserStandingData {
    String studentId;
    String classroomId;
    double finalGrade;
    String finalGradeFormatted;
    HashMap<ParentCategory, Double> breakdown = new HashMap<>();

    public UserStandingData(String classroomId, String studentId) {
        this.classroomId = classroomId;
        this.studentId = studentId;
    }

    public void addCategoryScore(ParentCategory category, double score) {
        breakdown.put(category, score);
    }

    public void printToLogcat() {
        Log.d("UserStandingData", "From classroom: " + classroomId);
        Log.d("UserStandingData", "Student issued: " + studentId);
        Log.d("UserStandingData", "Grade Breakdown:");
        for (Map.Entry<ParentCategory, Double> entry : breakdown.entrySet()) {
            String key = entry.getKey().getName();
            Double value = entry.getValue();
            Log.d("UserStandingData", "  - " + key + ": " + value);
        }
        Log.d("UserStandingData", "From classroom: " + classroomId);
    }

}
