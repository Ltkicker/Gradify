package com.github.ltkicker.gradify.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.data.users.UserCacheData;

public class MenuActivity extends AppCompatActivity {
    String demographic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!UserCacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
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
            Intent intent = new Intent(this, ClassDashboardActivity.class);
            startActivity(intent);
        }

    }


}
