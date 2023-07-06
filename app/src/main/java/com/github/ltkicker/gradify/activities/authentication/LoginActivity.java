package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.Gradify;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CacheData.isAuthenticated()) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity5_login);

        TextView bigLoginTxt = findViewById(R.id.loginBigTxt);
        ImageView loginImg = findViewById(R.id.loginImg);
        if(CacheData.isTeacher()) {
            bigLoginTxt.setText("Teacher Login:");
            loginImg.setImageResource(R.drawable.img_teacher_2);
        } else {
            bigLoginTxt.setText("Student Login:");
            loginImg.setImageResource(R.drawable.img_student_log_in);
        }

        TextView signin = findViewById(R.id.clickable_signup);
        signin.setOnClickListener(view -> loginUser());

        TextView signup = findViewById(R.id.textsignup);
        signup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            Gradify.addVisitedAuthActivity(this);
            startActivity(intent);
        });

    }

    private void loginUser() {
        TextInputEditText etUsername = findViewById(R.id.login_email1);
        TextInputEditText etPassword = findViewById(R.id.login_password);
        String username = Objects.requireNonNull(etUsername.getText()).toString();
        String password = Objects.requireNonNull(etPassword.getText()).toString();
        Log.d("LOL", username);
        FirebaseDatabase.getInstance().getReference("users").child(username).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("LOL", "yes");
                if(snapshot.exists()) {
                    String email = snapshot.getValue(String.class);
                    Log.d("LOL", email);
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    CacheData.setUsername(username);
                                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Handle login errors
                                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "User is not registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
