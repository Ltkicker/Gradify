package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClassAddStudentManualActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9f_add_manually_teacher);
        Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        TextInputEditText etLastName = findViewById(R.id.input_lastname);
        TextInputEditText etFirstName = findViewById(R.id.input_firstname);
        TextInputEditText etMiddleName = findViewById(R.id.input_middlename);
        TextInputEditText etIdNum = findViewById(R.id.input_idnum);

        String lastName = etLastName.getText().toString();
        String firstName = etFirstName.getText().toString();
        String middleName = etMiddleName.getText().toString();
        String idNum = etIdNum.getText().toString();

        TextView addStudent = findViewById(R.id.click_save);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.addStudent(idNum);
            }
        });
    }
}
