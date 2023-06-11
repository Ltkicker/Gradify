package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.users.Teacher;
import com.github.ltkicker.gradify.data.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String demographic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_signup);
        mAuth = FirebaseAuth.getInstance();

        demographic = getIntent().getStringExtra("demographic");

        if (mAuth.getCurrentUser() != null) {
            showMenu();
            finish();
            return;
        }

        TextView signup = findViewById(R.id.clickable_signup2);
        signup.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {

        TextInputEditText etLastName = findViewById(R.id.input_last_name);
        TextInputEditText etFirstName = findViewById(R.id.input_first_name);
        TextInputEditText etMiddleName = findViewById(R.id.input_middle_name);
        TextInputEditText etSuffixName = findViewById(R.id.input_suffix);
        TextInputEditText etEmail = findViewById(R.id.input_email);
        TextInputEditText etUsername = findViewById(R.id.input_username);
        TextInputEditText etPassword = findViewById(R.id.input_password);

        String lastName = etLastName.getText().toString();
        String firstName = etFirstName.getText().toString();
        String middleName = etMiddleName.getText().toString();
        String suffixName = etSuffixName.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        boolean isTeacher;

        if(demographic.equals("teacher")) {
            isTeacher = true;
        } else {
            isTeacher = false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(isTeacher, lastName, firstName, middleName, suffixName, email, username);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showMenu();
                                        }
                                    });
                            showMenu();
                        } else {
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
