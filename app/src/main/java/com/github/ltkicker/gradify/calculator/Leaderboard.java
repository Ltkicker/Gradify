package com.github.ltkicker.gradify.calculator;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Leaderboard {
    public Leaderboard(){

    }

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public void createClass() {
        reference.child("classrooms").child("teacher").setValue("Admin");
    }

}


