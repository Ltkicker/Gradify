package com.github.ltkicker.gradify.activities.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassOverviewActivity;

public class LeaderboardTopScorers extends AppCompatActivity {
    Button backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4_top_scorers_teacher_student);

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
