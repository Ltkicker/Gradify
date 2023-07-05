package com.github.ltkicker.gradify.activities.leaderboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.leaderboard.RecordedScoresAdapter;
import com.github.ltkicker.gradify.data.leaderboard.RecordedScoresInterface;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.github.ltkicker.gradify.data.leaderboard.TopScorerAdapter;

import java.util.ArrayList;

public class RecordedScoresActivity extends AppCompatActivity implements RecordedScoresInterface {
    private RecyclerView recordedScoresList;
    private RecordedScoresAdapter adapter;
    private ArrayList<SubCategory> subCategories;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4_top_scorers_teacher_student);

        recordedScoresList = findViewById(R.id.recordedScoresList);
        recordedScoresList.setLayoutManager(new LinearLayoutManager(this));
        recordedScoresList.setBackgroundResource(android.R.color.transparent);
        subCategories = new ArrayList<>();
        adapter = new RecordedScoresAdapter(this, subCategories, this);
        recordedScoresList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {

    }
}
