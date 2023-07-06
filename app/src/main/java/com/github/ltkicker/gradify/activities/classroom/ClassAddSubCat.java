package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.classrooms.UnregisteredSubCat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassAddSubCat extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_addsubcat_editrename_teacher);
        Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        TextInputEditText etSubjectCode = findViewById(R.id.input_subject_code4);
        TextInputEditText etSubjectsection = findViewById(R.id.input_subject_section2);
        TextInputEditText etTitle = findViewById(R.id.input_subcategory_title);
        TextInputEditText etSubDate = findViewById(R.id.subcategory_date);
        TextInputEditText etTotalPoints = findViewById(R.id.total_points);

        String subcode = etSubjectCode.getText().toString();
        String subsec = etSubjectsection.getText().toString();
        String tittle = etTitle.getText().toString();
        String date = etSubDate.getText().toString();
        String points = etTotalPoints.getText().toString();

        TextView addsubcat = findViewById(R.id.clickable_save);

        addsubcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeSubcat(subcode, subsec, tittle, date, points, result);
            }
        });
    }

    private void storeSubcat(String subcode, String subsec, String tittle, String date, String points,  Classroom classroom) {
        DatabaseReference cRef = FirebaseDatabase.getInstance().getReference("Unregistered");
        UnregisteredSubCat unregisteredSubCat = new UnregisteredSubCat(subcode, subsec, tittle, date, points);
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
                                    ArrayList<UnregisteredSubCat> list;
                                    if(snapshot.exists()) {
                                        list = snapshot.getValue(new GenericTypeIndicator<ArrayList<UnregisteredSubCat>>() {});
                                        list.add(unregisteredSubCat);
                                    } else {
                                        list = new ArrayList<>();
                                        list.add(unregisteredSubCat);
                                    }
                                    cRef.child(key).setValue(list)
                                            .addOnSuccessListener(unused -> {
                                                Toast.makeText(ClassAddSubCat.this, "Successfully added subcategory with " + subcode, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ClassAddSubCat.this, ClassStudentsActivity.class);
                                                startActivity(intent);
                                                finish();
                                            })
                                            .addOnFailureListener(e -> Toast.makeText(ClassAddSubCat.this, "Can't connect to Gradify Database", Toast.LENGTH_SHORT).show());
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
