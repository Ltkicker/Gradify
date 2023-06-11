package com.github.ltkicker.gradify.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.data.users.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
        Intent intent = null;
        if (temp.equals("HOMEPAGE")) {
            intent = new Intent(this, LogoutActivity.class);
        } else if (temp.equals("CLASSDASH")) {
            intent = new Intent(this, ClassDashboardActivity.class);
        }
        startActivity(intent);
    }

}
