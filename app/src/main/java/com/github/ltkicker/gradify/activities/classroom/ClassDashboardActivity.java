package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.Teacher;
import com.github.ltkicker.gradify.fragments.PopupFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassDashboardActivity extends AppCompatActivity {
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


        Classroom newClass = new Classroom(classCode, classSection, classDesc, roomNo, building);

        DatabaseReference classrooms = FirebaseDatabase.getInstance().getReference("classrooms");
        String key = classrooms.push().getKey();
        classrooms.child(key).setValue(newClass);
    }
}



