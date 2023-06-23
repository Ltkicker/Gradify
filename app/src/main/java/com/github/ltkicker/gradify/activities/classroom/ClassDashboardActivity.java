package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.database.DataFetcher;
import com.github.ltkicker.gradify.data.users.UserCacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassDashboardActivity extends AppCompatActivity {

    ArrayList<Classroom> classrooms = new ArrayList<>();
    DatabaseReference dRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserCacheData.isTeacher()) {
            dRef = FirebaseDatabase.getInstance().getReference().child("users").child(UserCacheData.getUsername()).child("classrooms").child("asTeacher");
            dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getChildrenCount() > 0) {
                        setContentView(R.layout.activity9_yourclass_teacher);
                    } else {
                        Intent intent = new Intent(ClassDashboardActivity.this, ClassEditActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            DataFetcher dataFetcher = data -> {
                classrooms.clear();
                classrooms = data;
            };
            fetchClasses(dataFetcher);
            Log.d("All Classes", classrooms.toString());
        } else {
            setContentView(R.layout.activity9_yourclass_teacher);
        }
    }

    public void fetchClasses(DataFetcher dataFetcher) {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<Classroom> allClasses = new ArrayList<>();
                    snapshot.getChildren().forEach(classroom -> allClasses.add(classroom.getValue(Classroom.class)));
                    dataFetcher.onDataLoaded(allClasses);
                } else {
                    Log.e("Fetch Error", UserCacheData.getUsername() + " tried to fetch: " + snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
