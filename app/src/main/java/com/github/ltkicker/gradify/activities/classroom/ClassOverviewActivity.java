package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClassOverviewActivity extends AppCompatActivity {
    DatabaseReference cRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9g_add_category_teacher);

        TextView subjectDescription = findViewById(R.id.subjectdesciption);
        TextView classCode = findViewById(R.id.text_mainsubjectcode);

        Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        subjectDescription.setText(result.getTitle());
        classCode.setText(result.getCode());
        addGradeCategory(result, "Exams", "Exam");
    }

    private void addGradeCategory(Classroom classroom, String plural, String singular) {
        FirebaseDatabase.getInstance().getReference("classrooms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if (classroom == childSnapshot.getValue(Classroom.class)) {
                        FirebaseDatabase.getInstance().getReference("classrooms").child(childSnapshot.getKey()).child("gradeCategories").child(plural).setValue(singular);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addGradeCategory(Classroom classroom, String plural) {
        addGradeCategory(classroom, plural, plural);
    }
}
