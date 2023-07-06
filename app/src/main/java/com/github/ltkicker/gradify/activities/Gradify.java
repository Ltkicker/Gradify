package com.github.ltkicker.gradify.activities;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.leaderboard.StudentOverallStandingActivity;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;

import android.content.Intent;

import java.util.ArrayList;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity0_main_loading_withlogo);

        FirebaseUtils.init();


        // Add a delay before showing the next loading screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        @Override
            public void run() {
                startActivity (new Intent(Gradify.this, StudentOverallStandingActivity.class));
                finish();
            }

        }, 1000);

    }
    private static final ArrayList<Activity> visitedAuthActivities = new ArrayList<>();
    public static void addVisitedAuthActivity(Activity activity) {
        visitedAuthActivities.add(activity);
    }

    public static void clearAuthActivity() {
        for (Activity activity : visitedAuthActivities) {
            activity.finish();
        }
    }
}
