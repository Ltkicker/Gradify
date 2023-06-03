package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;

public class AuthPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        setContentView(R.layout.activity_authportal);
        ImageButton studentBtn = findViewById(R.id.imgstudent);
        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthPortalActivity.this, LoginActivity.class);
                intent.putExtra("demographic", "student");
                startActivity(intent);
            }
        });
        ImageButton teacherBtn = findViewById(R.id.imgteacher);
        teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthPortalActivity.this, LoginActivity.class);
                intent.putExtra("demographic", "teacher");
                startActivity(intent);
            }
        });
    }
}