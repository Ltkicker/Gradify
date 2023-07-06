package com.github.ltkicker.gradify.calculator;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.calculator.listeners.GradesRefresherListener;
import com.github.ltkicker.gradify.data.grades.UserStandingData;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentGradeManager{
    private static DatabaseReference dRef;
    private static DatabaseReference sRef;
    private static String classroomId = "NZItQ2M6m_y9IXcgOW7";


    public StudentGradeManager() {

    }

    public StudentGradeManager(String classroomId) {

    }

    public static void calculateSingleStudentGrade(String studentId) {
        FirebaseDatabase.getInstance().getReference("grades").child(classroomId)
                .child("students").child("studentId").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public static void startCalculate(GradesRefresherListener listener) {
        dRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId);
        dRef.child("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    // firstSnapshot: studentId
                    // secondSnapshot: categories
                    // thirdSnapshot: subcategories
                    HashMap<String, UserStandingData> allStudentData = new HashMap<>();
                    for(DataSnapshot firstSnapshot : snapshot.getChildren()) {
                        UserStandingData userStanding = new UserStandingData(firstSnapshot.getKey(), classroomId);
                        for(DataSnapshot secondSnapshot : firstSnapshot.getChildren()) {
                            String categoryStr = secondSnapshot.getKey();
                            ParentCategory category = new ParentCategory(categoryStr, "", 0.1);
                            double sumCategoryGrade = 0;
                            long n = secondSnapshot.getChildrenCount();
                            for(DataSnapshot thirdSnapshot : secondSnapshot.getChildren()) {
                                sumCategoryGrade += thirdSnapshot.getValue(Integer.class);
                            }
                            double categoryGrade = (sumCategoryGrade / n) * 0.1;
                            userStanding.addCategoryScore(category, categoryGrade);
                        }
                        allStudentData.put(firstSnapshot.getKey(), userStanding);
                    }
                    listener.onRefresh(allStudentData);


                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getGrades(GradesRefresherListener listener) {
        classroomId = "NZItQ2M6m_y9IXcgOW7";
        sRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId).child("students");
        dRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId);
        dRef.child("parentcategory").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for(DataSnapshot categorySnapshot : snapshot.getChildren()) {

                        Double categoryPercent = categorySnapshot.child("percentage").getValue(Double.class);
                        String categoryName = categorySnapshot.child("name").getValue(String.class);
                        ParentCategory category = new ParentCategory(categorySnapshot.getKey(), categoryName, categoryPercent);
                        sRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                HashMap<String, UserStandingData> allStudentData = new HashMap<>();
                                for(DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                    UserStandingData userStandingData = new UserStandingData(studentSnapshot.getKey(), classroomId);
                                    DataSnapshot parentCategory = studentSnapshot.child(categorySnapshot.getKey());
                                    double sumCategory = 0;
                                    long n = parentCategory.getChildrenCount();
                                    for(DataSnapshot subCategory : parentCategory.getChildren()) {
                                        sumCategory += subCategory.getValue(Double.class);
                                    }
                                    double totalParentPercentage = (sumCategory / n) * categoryPercent;
                                    userStandingData.addCategoryScore(category, totalParentPercentage);
                                    allStudentData.put(studentSnapshot.getKey(), userStandingData);
                                }
                                listener.onRefresh(allStudentData);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
