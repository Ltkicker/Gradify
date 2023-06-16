package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClassEditActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9b_class_info_teacher);

        TextView createClass = findViewById(R.id.clickable_createclass);
        createClass.setOnClickListener(view -> registerClass());

    }

    private void showPopup() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.popuptemp, new PopupFragment());
        fragmentTransaction.commit();
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
        Classroom newClass = new Classroom(classCode, classSection, classDesc, roomNo, building, FirebaseAuth.getInstance().getUid());

        DatabaseReference classrooms = FirebaseDatabase.getInstance().getReference("classrooms");
        String key = classrooms.push().getKey();
        classrooms.child(key).setValue(newClass);

        // Add classroom to list of classrooms handled by teacher
        DatabaseReference fbClasses = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).child("classrooms").child("asTeacher");
        fbClasses.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list;
                if(snapshot.exists()) {
                    list = snapshot.getValue(new GenericTypeIndicator<ArrayList<String>>() {});
                    list.add(key);
                    Log.d("Property", "Existing Class");
                } else {
                    list = new ArrayList<>();
                    list.add(key);
                    Log.d("Property", "New Class");
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



