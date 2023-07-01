package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.UserCacheData;
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
        if(!UserCacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity9g_add_category_teacher);
        TextView subjectDescription = findViewById(R.id.subjectdesciption);
        TextView classCode = findViewById(R.id.text_mainsubjectcode);
        TextView manageStudents = findViewById(R.id.manage_students1);


//        });
//        FirebaseDatabase.getInstance().getReference("Classroom").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                subjectDescription.setText(snapshot.child("Subject Description").getValue(String.class));
//                classCode.setText(snapshot.child("Subject Code").getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

       Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        subjectDescription.setText(result.getTitle());
        classCode.setText(result.getCode());

        manageStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassOverviewActivity.this, ClassStudentsActivity.class);
                intent.putExtra("CLASS_ID", result);
                startActivity(intent);
            }
        });
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
