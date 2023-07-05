package com.github.ltkicker.gradify.activities.leaderboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.github.ltkicker.gradify.data.leaderboard.TopScorerAdapter;
import com.github.ltkicker.gradify.data.leaderboard.TopScorerInterface;

import java.util.ArrayList;

public class LeaderboardTopScorers extends AppCompatActivity implements TopScorerInterface {
    private RecyclerView topScorerList;
    private TopScorerAdapter adapter;
    private ArrayList<SubCategory> subCategories;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4_top_scorers_teacher_student);

        topScorerList = findViewById(R.id.topScorerList);
        topScorerList.setLayoutManager(new LinearLayoutManager(this));
        topScorerList.setBackgroundResource(android.R.color.transparent);
        subCategories = new ArrayList<>();
        adapter = new TopScorerAdapter(this, subCategories, this);
        topScorerList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {

    }
}
