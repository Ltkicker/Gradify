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
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;

public class StudentOverallStandingActivity extends AppCompatActivity {
    Button backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_overall_standing_student);

        backbutton = (Button)findViewById(R.id.img_backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentOverallStandingActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
