package com.github.ltkicker.gradify.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.data.users.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton classDashboard = findViewById(R.id.menu_addclass);
        classDashboard.setOnClickListener(view -> navigate("CLASSDASH"));

        ImageButton homepage = findViewById(R.id.menu_homepage);
        homepage.setOnClickListener(view -> navigate("HOMEPAGE"));

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    private void navigate(String temp) {
        // replacean rani ug enums, temp raning if else
        Intent intent = null;
        if (temp.equals("HOMEPAGE")) {
            intent = new Intent(this, LogoutActivity.class);
        } else if (temp.equals("CLASSDASH")) {
            intent = new Intent(this, ClassDashboardActivity.class);
        }
        startActivity(intent);
    }

}
