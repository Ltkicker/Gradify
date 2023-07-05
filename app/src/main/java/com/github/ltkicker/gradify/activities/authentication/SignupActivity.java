package com.github.ltkicker.gradify.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.users.User;
import com.github.ltkicker.gradify.data.users.UserCacheData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_signup);

        if (UserCacheData.isAuthenticated()) {
            showMenu();
            finish();
            return;
        }

        TextView signup = findViewById(R.id.clickable_signup2);
        signup.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {

        TextInputEditText etLastName = findViewById(R.id.login_email1);
        TextInputEditText etFirstName = findViewById(R.id.input_first_name);
        TextInputEditText etMiddleName = findViewById(R.id.input_middle_name);
        TextInputEditText etIdNumber = findViewById(R.id.input_suffix);
        TextInputEditText etEmail = findViewById(R.id.input_email);
        TextInputEditText etUsername = findViewById(R.id.input_username);
        TextInputEditText etPassword = findViewById(R.id.input_password);

        String lastName = etLastName.getText().toString();
        String firstName = etFirstName.getText().toString();
        String middleName = etMiddleName.getText().toString();
        String idNumber = etIdNumber.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        User user = new User(UserCacheData.isTeacher(), lastName, firstName, middleName, idNumber, email, username);
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(username)
                                .setValue(user).addOnCompleteListener(t -> showMenu());
                    } else {
                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
