package com.github.ltkicker.gradify.activities.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;

import com.github.ltkicker.gradify.data.leaderboard.GradeSubCategoryAdapter;
import com.github.ltkicker.gradify.data.leaderboard.GradeSubCategoryInterface;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity implements GradeSubCategoryInterface {
    Button backbutton;
    private RecyclerView subCategList;
    private ArrayList<SubCategory> subCategories;
    private GradeSubCategoryAdapter adapter;

    private DatabaseReference dRef;

    String keyReference;
    private ArrayList<String> subKey;

    private String classroomId;
    TextView subjectCodeTxt;
    TextView subjectSectionTxt;
    TextView subjectDescTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b0_leaderboard_teacher);

        subKey = new ArrayList<>();

        classroomId = "-NZItQ2M6m_y9IXcgOW7";

        // Kato naning mag change2 ug category pero diari rako taman kay bungkag ang front end ani
        keyReference = "parentcategory1";

        subjectCodeTxt = findViewById(R.id.subject_code);
        subjectSectionTxt = findViewById(R.id.subject_section);
        subjectDescTxt = findViewById(R.id.subject_description);

        backbutton = (Button)findViewById(R.id.img_backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderboardActivity.this, ClassDashboardActivity.class);
                startActivity(intent);
            }
        });

        subCategList = findViewById(R.id.subCategList);
        subCategList.setLayoutManager(new LinearLayoutManager(this));
        subCategList.setBackgroundResource(android.R.color.transparent);
        subCategories = new ArrayList<>();
        adapter = new GradeSubCategoryAdapter(this, subCategories, this);
        subCategList.setAdapter(adapter);

        refreshList();

        FirebaseDatabase.getInstance().getReference("classrooms").child(classroomId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String subjectCode = snapshot.child("code").getValue(String.class);
                        String subjectSection = snapshot.child("section").getValue(String.class);
                        String subjectDesc = snapshot.child("title").getValue(String.class);

                        subjectCodeTxt.setText(subjectCode);
                        subjectSectionTxt.setText(subjectSection);
                        subjectDescTxt.setText(subjectDesc);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        TextView attendanceTxt = findViewById(R.id.parent_category1);
        attendanceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyReference = "parentcategory1";
                refreshList();
            }
        });

        TextView quizzesTxt = findViewById(R.id.parent_categor2);
        quizzesTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyReference = "parentcategory2";
                refreshList();
            }
        });

        TextView projectTxt = findViewById(R.id.parent_category3);
        projectTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyReference = "parentcategory4";
                refreshList();
            }
        });

        TextView othersTxt = findViewById(R.id.parent_category4);
        if(CacheData.isTeacher()) {
            othersTxt.setText("Exams");
            othersTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    keyReference = "parentcategory3";
                    refreshList();
                }
            });
        } else {
            othersTxt.setText("Others");
            othersTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    keyReference = "parentcategory3";
                    refreshList(true);
                }
            });
        }

    }

    private void refreshList(boolean others) {
        dRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId)
                .child("categories").child(keyReference).child("subcategories");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    subCategories.clear();
                    subKey.clear();
                    if(others) {
                        subKey.add("Overall");
                        SubCategory overall = new SubCategory();
                        overall.setTitle("Overall Standing");
                        subCategories.add(overall);
                    }
                    for(DataSnapshot subSnapshot : snapshot.getChildren()) {
                        subCategories.add(subSnapshot.getValue(SubCategory.class));
                        subKey.add(subSnapshot.getKey());

                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void refreshList() {
        refreshList(false);
    }

    @Override
    public void onItemClick(int position) {
        if(!CacheData.isTeacher() && keyReference.equalsIgnoreCase("parentcategory3") && position == 0) {
            Intent intent = new Intent(LeaderboardActivity.this, StudentOverallStandingActivity.class);
            startActivity(intent);
            return;
        }
        Intent intent = new Intent(this, LeaderboardTopScorers.class);
        intent.putExtra("PARENTCATEGORYID", keyReference);
        intent.putExtra("SUBCATEGORY", subCategories.get(position));
        intent.putExtra("SUBCATEGORYID", subKey.get(position));
        startActivity(intent);
    }
}
