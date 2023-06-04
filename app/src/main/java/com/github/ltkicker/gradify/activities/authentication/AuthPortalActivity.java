package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.MenuActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AuthPortalActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            showMenu();
            finish();
            return;
        }

        setContentView(R.layout.activity_authportal);
        ImageButton studentBtn = findViewById(R.id.imgstudent);
        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthPortalActivity.this, LoginActivity.class);
                intent.putExtra("demographic", "student");
                startActivity(intent);
                finish();
            }
        });
        ImageButton teacherBtn = findViewById(R.id.imgteacher);
        teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthPortalActivity.this, LoginActivity.class);
                intent.putExtra("demographic", "teacher");
                startActivity(intent);
                finish();
            }
        });
    }
    private void showMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}