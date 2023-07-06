package com.github.ltkicker.gradify.activities.leaderboard;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;

import com.github.ltkicker.gradify.data.grades.GradingInstanceData;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.github.ltkicker.gradify.data.leaderboard.TopScorerAdapter;
import com.github.ltkicker.gradify.data.leaderboard.TopScorerInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardTopScorers extends AppCompatActivity implements TopScorerInterface {
    private RecyclerView topScorerList;
    private TopScorerAdapter adapter;
    private ArrayList<GradingInstanceData> studentScores;
    private LinkedHashMap<String, Double> scores;

    private DatabaseReference dref;
=======
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassOverviewActivity;

public class LeaderboardTopScorers extends AppCompatActivity {
    Button backbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4_top_scorers_teacher_student);

        SubCategory subCategory = (SubCategory) getIntent().getSerializableExtra("SUBCATEGORY");
        String subCategoryKey = getIntent().getStringExtra("SUBCATEGORYID");
        String parentCategoryKey = getIntent().getStringExtra("PARENTCATEGORYID");



        dref = FirebaseDatabase.getInstance().getReference("grades").child("NZItQ2M6m_y9IXcgOW7")
                .child("students");

        topScorerList = findViewById(R.id.topScorerList);
        topScorerList.setLayoutManager(new LinearLayoutManager(this));
        topScorerList.setBackgroundResource(android.R.color.transparent);
        studentScores = new ArrayList<>();
        adapter = new TopScorerAdapter(this, studentScores, this);
        topScorerList.setAdapter(adapter);

        TextView subcategtitle = findViewById(R.id.sub_category_title);
        subcategtitle.setText(subCategory.getName());


        TextView firstplace = findViewById(R.id.student_firstname_top1);
        TextView secondplace = findViewById(R.id.student_firstname_top2);
        TextView thirdplace = findViewById(R.id.student_firstname_top3);

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    LinkedHashMap<String, Double> values = new LinkedHashMap<>();
                    for(DataSnapshot studentSnapshot : snapshot.getChildren()) {
                        String studentId = studentSnapshot.getKey();
                        Double studentScore = studentSnapshot.child(parentCategoryKey).child(subCategoryKey)
                                .getValue(Double.class);
                        values.put(studentId, studentScore);
                    }
                    scores = sortByValues(values);
                    int i = 1;
                    for (String key : scores.keySet()) {
                        Double value = scores.get(key);
                        GradingInstanceData data = new GradingInstanceData(key, value);
                        data.setPlace(i);
                        if(i == 1) {
                            firstplace.setText(key);
                        } else if (i == 2) {
                            secondplace.setText(key);
                        } else if (i == 3) {
                            thirdplace.setText(key);
                        }

                        Log.d("AWEVAWEV", i + ". " + key + ": " + value);
                        i++;
                        studentScores.add(data);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }

    private static LinkedHashMap<String, Double> sortByValues(LinkedHashMap<String, Double> map) {
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        return sortedMap;

        backbutton = (Button)findViewById(R.id.img_backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderboardTopScorers.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
