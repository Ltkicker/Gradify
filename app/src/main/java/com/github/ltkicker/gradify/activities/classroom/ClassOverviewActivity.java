package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class ClassOverviewActivity extends AppCompatActivity {
    DatabaseReference cRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9g_add_category_teacher);

        TextView subjectDescription = findViewById(R.id.subjectdescription);
        TextView classCode = findViewById(R.id.text_mainsubjectcode);

        Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        subjectDescription.setText(result.getTitle());
        classCode.setText(result.getCode());
    }
}
