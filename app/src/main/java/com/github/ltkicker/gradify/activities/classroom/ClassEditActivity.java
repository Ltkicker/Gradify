package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassEditActivity extends AppCompatActivity {
    DatabaseReference fbClasses;
    Button backbutton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity9b_class_info_teacher);

        TextView createClass = findViewById(R.id.clickable_createclass);
        createClass.setOnClickListener(view -> registerClass());
      
        backbutton = (Button)findViewById(R.id.img_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassEditActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerClass() {
        TextInputEditText etClassCode = findViewById(R.id.input_subject_code);
        TextInputEditText etClassSection = findViewById(R.id.input_subject_section);
        TextInputEditText etClassDesc = findViewById(R.id.input_subject_code3);
        TextInputEditText etRoomNo = findViewById(R.id.input_room_no);
        TextInputEditText etBuilding = findViewById(R.id.input_building);

        String classCode = etClassCode.getText().toString();
        String classSection = etClassSection.getText().toString();
        String classDesc = etClassDesc.getText().toString();
        String roomNo = etRoomNo.getText().toString();
        String building = etBuilding.getText().toString();


        // Create classroom then add to database

        DatabaseReference classrooms = FirebaseDatabase.getInstance().getReference("classrooms");
        String key = classrooms.push().getKey();
        Classroom newClass = new Classroom(classCode, classSection, classDesc, roomNo, building, CacheData.getUsername(), key);
        classrooms.child(key).setValue(newClass);

        // Add classroom to list of classrooms handled by teacher
        fbClasses = FirebaseDatabase.getInstance().getReference("users").child(CacheData.getUsername()).child("classrooms").child("asTeacher");
        fbClasses.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list;
                if(snapshot.exists()) {
                    list = snapshot.getValue(new GenericTypeIndicator<ArrayList<String>>() {});
                    list.add(key);
                } else {
                    list = new ArrayList<>();
                    list.add(key);
                }
                fbClasses.setValue(list)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(ClassEditActivity.this, "Successfully created " + classCode, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ClassEditActivity.this, ClassDashboardActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> Toast.makeText(ClassEditActivity.this, "Can't connect to Gradify Database", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}



