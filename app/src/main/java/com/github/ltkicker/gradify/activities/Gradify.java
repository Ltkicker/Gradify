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

import android.content.Intent;

import java.util.ArrayList;

public class Gradify extends AppCompatActivity {


    private static final ArrayList<Activity> visitedAuthActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity0_main_loading_withlogo);

        FirebaseUtils.init();


    }

    public static void addVisitedAuthActivity(Activity activity) {
        visitedAuthActivities.add(activity);
    }

    public static void clearAuthActivity() {
        for (Activity activity : visitedAuthActivities) {
            activity.finish();
        }

    }



}
