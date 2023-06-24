package com.github.ltkicker.gradify.data.grades;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GradingSystem {
    private HashMap<String, String> categories; // Plural, Singular
    private HashMap<String, ArrayList<GradingInstanceData>> history; // Category, GradeInstance


    public GradingSystem(){
    }


}
