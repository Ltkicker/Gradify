package com.github.ltkicker.gradify.data.users;

import android.content.Intent;
import android.widget.Toast;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.LoginActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UserCacheData {
    private static final Queue<Intent> visitedActivities = new LinkedList<>();
    private static String username;
    private static boolean asTeacher;

    public static void updateUserCache(String username, boolean asTeacher) {
        UserCacheData.username = username;
        UserCacheData.asTeacher = asTeacher;
    }

    public static void addVisitedActivity(Intent intent) {
        visitedActivities.add(intent);
    }

    public static void removeVisitedActivity(Intent intent) {
        visitedActivities.remove(intent);
    }

    public static String getUsername(){
        return username;
    }
}
