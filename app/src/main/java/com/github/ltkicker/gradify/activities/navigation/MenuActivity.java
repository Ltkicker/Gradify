package com.github.ltkicker.gradify.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.authentication.LoginActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassEditActivity;
import com.github.ltkicker.gradify.data.users.UserCacheData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    String demographic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        demographic = getIntent().getStringExtra("demographic");
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_main_menu);

        ImageButton classDashboard = findViewById(R.id.menu_addclass);
        classDashboard.setOnClickListener(view -> navigate("CLASSDASH"));

        ImageButton homepage = findViewById(R.id.menu_homepage);
        homepage.setOnClickListener(view -> navigate("HOMEPAGE"));

    }

    private void navigate(String temp) {
        // replacean rani ug enums, temp raning if else


        if (temp.equals("HOMEPAGE")) {
            Intent intent = new Intent(this, LogoutActivity.class);
            startActivity(intent);
        } else if (temp.equals("CLASSDASH")) {
            DatabaseReference classrooms;
            if(demographic.equals("teacher")) {
                classrooms = FirebaseDatabase.getInstance().getReference("users").child(UserCacheData.getUsername()).child("classrooms").child("asTeacher");
            } else {
                classrooms = FirebaseDatabase.getInstance().getReference("users").child(UserCacheData.getUsername()).child("classrooms").child("asStudent");
            }
            classrooms.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        Toast.makeText(MenuActivity.this, "empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MenuActivity.this, ClassDashboardActivity.class);
                        intent.putExtra("demographic", demographic);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


}
