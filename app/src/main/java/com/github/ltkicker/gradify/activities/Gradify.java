package com.github.ltkicker.gradify.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;

import android.content.Intent;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity00_allpagesforloading);

        final LinearLayout loadingContainer = findViewById(R.id.loadingContainer);
        final View loading1 = findViewById(R.id.activity0_main_loading_withlogo);
        final View loading2 = findViewById(R.id.activity1_main_loading);
        final View loading3 = findViewById(R.id.activity2_main_loading_withlogo);

        // Hide all loading screens initially, except the first one
        loading2.setVisibility(View.GONE);
        loading3.setVisibility(View.GONE);

        // Add a delay before showing the next loading screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading1.setVisibility(View.GONE);
                loading2.setVisibility(View.VISIBLE);
            }
        }, 2000); // 2000 milliseconds delay

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading2.setVisibility(View.GONE);
                loading3.setVisibility(View.VISIBLE);
            }
        }, 4000); // 4000 milliseconds delay

        Intent intent = new Intent(Gradify.this, AuthPortalActivity.class);
        startActivity(intent);
        finish();
    }



}
