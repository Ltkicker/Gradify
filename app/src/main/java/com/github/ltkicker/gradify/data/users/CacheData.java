package com.github.ltkicker.gradify.data.users;

import android.util.Log;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.activities.leaderboard.StudentOverallStandingActivity;
import com.github.ltkicker.gradify.calculator.StudentGradeManager;
import com.github.ltkicker.gradify.calculator.listeners.GradesRefresherListener;
import com.github.ltkicker.gradify.data.grades.UserStandingData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CacheData {
    private static FirebaseAuth mAuth;
    private static String username;
    private static boolean asTeacher;


    public static void updateUserCache(String username, boolean asTeacher) {
        CacheData.username = username;
        CacheData.asTeacher = asTeacher;
    }


    public static String getUsername(){
        return CacheData.username;
    }
    public static void setUsername(String username){
        CacheData.username = username;
    }

    public static boolean isAuthenticated() {
        if(CacheData.getUsername() == null) {
            return false;
        }
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void setAsTeacher(boolean asTeacher) {
        CacheData.asTeacher = asTeacher;
    }

    public static boolean isTeacher() {
        return CacheData.asTeacher;
    }

    public static void init() {
    }

//    public static User getUser(String idNumber) {
//        return users.get(idNumber);
//    }
}
