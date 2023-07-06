package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.classrooms.ClassListAdapter;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.classrooms.StudentListAdapter;
import com.github.ltkicker.gradify.data.classrooms.StudentListInterface;
import com.github.ltkicker.gradify.data.users.User;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassStudentsActivity extends AppCompatActivity implements StudentListInterface {
    DatabaseReference dRef, cRef;
    RecyclerView studentList;
    ArrayList<String> studentsById;
    ArrayList<User> students;
    StudentListAdapter adapter;

    Button backbutton2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        studentsById = new ArrayList<>();
        students = new ArrayList<>();

        setContentView(R.layout.activity9c_yourstudent_teacher);
        studentList = findViewById(R.id.studentList);
        studentList.setLayoutManager(new LinearLayoutManager(this));
        studentList.setBackgroundResource(android.R.color.transparent);
        students = new ArrayList<>();
        adapter = new StudentListAdapter(this, students, this);
        studentList.setAdapter(adapter);

        Classroom result = (Classroom) getIntent().getSerializableExtra("CLASS_ID");

        ImageButton buttonAdd = findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassStudentsActivity.this, ClassAddStudentManualActivity.class);
                intent.putExtra("CLASS_ID", result);
                startActivity(intent);
            }
        });
        backbutton2 = (Button)findViewById(R.id.img_backbutton2);
        backbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassStudentsActivity.this, ClassOverviewActivity.class);
                startActivity(intent);
            }
        });

        cRef = FirebaseDatabase.getInstance().getReference("classrooms");
        dRef = FirebaseDatabase.getInstance().getReference("users");
        dRef.child(CacheData.getUsername()).child("classrooms").child("asTeacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    snapshot.getChildren().forEach(data -> {
                        studentsById.add(data.getValue(String.class));
                    });
                }
//                for (String s : studentsById) {
//                    cRef.child(s).child("students").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.exists()) {
//                                students.add(snapshot.getValue(User.class));
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}
