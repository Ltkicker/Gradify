package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        String demographic = getIntent().getStringExtra("demographic");
        if (demographic.equals("student")){
            setContentView(R.layout.activity_login_student);
        } else {
            setContentView(R.layout.activity_login_teacher);
        }

        TextView signup = findViewById(R.id.textsignup);
        signup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.putExtra("demographic", demographic);
            startActivity(intent);
        });


    }

}
