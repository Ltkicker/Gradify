package com.github.ltkicker.gradify.activities.authentication;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextInputEditText lastName = findViewById(R.id.input_last_name);
        TextInputEditText firstName = findViewById(R.id.input_first_name);
        TextInputEditText middleName = findViewById(R.id.input_mddle_name);
        TextInputEditText suffixName = findViewById(R.id.input_suffix);
        TextInputEditText email = findViewById(R.id.input_email);
        TextInputEditText username = findViewById(R.id.input_username);
        TextInputEditText password = findViewById(R.id.input_password);
    }
}
