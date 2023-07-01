package com.github.ltkicker.gradify.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;

import android.content.Intent;

import java.util.ArrayList;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_b4_top_scorers_teacher_student);
        //setContentView(R.layout.activity9f_add_manually_teacher);

        FirebaseUtils.init();

        // Add a delay before showing the next loading screen
        Handler handler = new Handler();
       /*   handler.postDelayed(new Runnable() {
        @Override
            public void run() {
                startActivity (new Intent(Gradify.this,AuthPortalActivity.class));
                finish();
            }

        }, 2000); // 4000 milliseconds delay

*/
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
