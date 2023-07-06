package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.LogoutActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.classrooms.ClassListAdapter;
import com.github.ltkicker.gradify.data.classrooms.ClassListInterface;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassDashboardActivity extends AppCompatActivity implements ClassListInterface {
//public class ClassDashboardActivity extends AppCompatActivity {
    ArrayList<Classroom> classrooms;
    ArrayList<String> classroomsById;
    DatabaseReference dRef;
    DatabaseReference cRef;
    RecyclerView classList;
    ClassListAdapter adapter;
    ImageButton addButton;

    Button backbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_yourclass);
//        ImageView image = findViewById(R.id.yourstudent_img2);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ClassDashboardActivity.this, ClassOverviewActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
      
        if(CacheData.isTeacher()) {
            dRef = FirebaseDatabase.getInstance().getReference("users").child(CacheData.getUsername()).child("classrooms").child("asTeacher");

        backbutton = (Button)findViewById(R.id.img_backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassDashboardActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

            dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.exists()) {
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
            dRef = FirebaseDatabase.getInstance().getReference("users").child(CacheData.getUsername()).child("classrooms").child("asStudent");
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
        Intent intent = new Intent(ClassDashboardActivity.this, ClassOverviewActivity.class);
        intent.putExtra("CLASS_ID", classrooms.get(position));
        startActivity(intent);
    }
}
