package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.users.UserCacheData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String demographic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demographic = getIntent().getStringExtra("demographic");
        if (demographic.equals("student")){
            setContentView(R.layout.activity4_login_student);
        } else {
            setContentView(R.layout.activity5_login_teacher);
        }

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
        TextView signin = findViewById(R.id.clickable_signup);
        signin.setOnClickListener(view -> {
            loginUser();
        });

        TextView signup = findViewById(R.id.textsignup);
        signup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.putExtra("demographic", demographic);
            startActivity(intent);
        });

    }

    private void loginUser() {
        TextInputEditText etUsername = findViewById(R.id.input_last_name);
        TextInputEditText etPassword = findViewById(R.id.input_password);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(username).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String email = snapshot.getValue(String.class);
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                        intent.putExtra("demographic", demographic);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Handle login errors
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
