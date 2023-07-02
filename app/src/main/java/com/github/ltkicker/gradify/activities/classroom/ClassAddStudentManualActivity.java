package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
                storeStudent(idNum, result);
            }
        });
    }

    private void storeStudent(String idNum, Classroom classroom) {
        DatabaseReference cRef = FirebaseDatabase.getInstance().getReference("classrooms");
        cRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot childSnapshot : snapshot.getChildren()) {
                        if(classroom.equals(childSnapshot.getValue(Classroom.class))) {
                            String key = childSnapshot.getKey();
                            cRef.child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ArrayList<String> list;
                                    if(snapshot.exists()) {
                                        list = snapshot.getValue(new GenericTypeIndicator<ArrayList<String>>() {});
                                        list.add(idNum);
                                    } else {
                                        list = new ArrayList<>();
                                        list.add(idNum);
                                    }
                                    cRef.child(key).setValue(list)
                                            .addOnSuccessListener(unused -> {
                                                Toast.makeText(ClassAddStudentManualActivity.this, "Successfully added student with " + idNum, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ClassAddStudentManualActivity.this, ClassStudentsActivity.class);
                                                startActivity(intent);
                                                finish();
                                            })
                                            .addOnFailureListener(e -> Toast.makeText(ClassAddStudentManualActivity.this, "Can't connect to Gradify Database", Toast.LENGTH_SHORT).show());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
