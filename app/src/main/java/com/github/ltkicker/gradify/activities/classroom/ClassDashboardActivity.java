package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.ClassListAdapter;
import com.github.ltkicker.gradify.data.classrooms.ClassListInterface;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.UserCacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassDashboardActivity extends AppCompatActivity implements ClassListInterface {

    ArrayList<Classroom> classrooms;
    ArrayList<String> classroomsById;
    DatabaseReference dRef;
    DatabaseReference cRef;
    RecyclerView classList;
    ClassListAdapter adapter;
    ImageButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_yourclass);
        if(UserCacheData.isTeacher()) {
            dRef = FirebaseDatabase.getInstance().getReference("users").child(UserCacheData.getUsername()).child("classrooms").child("asTeacher");
            dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getChildrenCount() <= 0) {
                        Intent intent = new Intent(ClassDashboardActivity.this, ClassEmptyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            dRef = FirebaseDatabase.getInstance().getReference("users").child(UserCacheData.getUsername()).child("classrooms").child("asStudent");
        }
        cRef = FirebaseDatabase.getInstance().getReference("classrooms");

        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassDashboardActivity.this, ClassEditActivity.class);
                startActivity(intent);
            }
        });

        classroomsById = new ArrayList<>();
        classrooms = new ArrayList<>();

        // Used to display the classrooms
        classList = findViewById(R.id.classList);
        classList.setLayoutManager(new LinearLayoutManager(this));
        classList.setBackgroundResource(android.R.color.transparent);
        ArrayList<Classroom> temp = new ArrayList<>();
        classrooms = new ArrayList<>();
        adapter = new ClassListAdapter(this, classrooms, this);
        classList.setAdapter(adapter);


        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    snapshot.getChildren().forEach(data -> {
                        classroomsById.add(data.getValue(String.class));
                    });
                }
                for (String s : classroomsById) {
                    cRef.child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            classrooms.add(snapshot.getValue(Classroom.class));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Database Error", error.toString());
            }
        });




    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ClassDashboardActivity.this, ClassStudentsActivity.class);
        intent.putExtra("CLASS_ID", classrooms.get(position));
        startActivity(intent);
    }
}
