package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.Gradify;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.users.CacheData;

public class AuthPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CacheData.isAuthenticated()) {
            Intent intent = new Intent(AuthPortalActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity3_authportal);

        Intent intent = new Intent(AuthPortalActivity.this, LoginActivity.class);
        ImageButton studentBtn = findViewById(R.id.imgstudent);
        studentBtn.setOnClickListener(view -> {
            CacheData.setAsTeacher(false);
            Gradify.addVisitedAuthActivity(this);
            startActivity(intent);
        });

        ImageButton teacherBtn = findViewById(R.id.imgteacher);
        teacherBtn.setOnClickListener(view -> {
            CacheData.setAsTeacher(true);
            Gradify.addVisitedAuthActivity(this);
            startActivity(intent);
        });
    }

}