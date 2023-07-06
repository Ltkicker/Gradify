package com.github.ltkicker.gradify.data.users;

import com.google.firebase.auth.FirebaseAuth;

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
}
