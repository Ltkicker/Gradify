package com.github.ltkicker.gradify.data.users;

import android.content.Intent;
import android.widget.Toast;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.LoginActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UserCacheData {
    private static FirebaseAuth mAuth;
    private static String username;
    private static boolean asTeacher;

    public static void updateUserCache(String username, boolean asTeacher) {
        UserCacheData.username = username;
        UserCacheData.asTeacher = asTeacher;
    }


    public static String getUsername(){
        return username;
    }

    public static boolean isAuthenticated() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void setAsTeacher(boolean asTeacher) {
        UserCacheData.asTeacher = asTeacher;
    }

    public static boolean isTeacher() {
        return UserCacheData.asTeacher;
    }
}
