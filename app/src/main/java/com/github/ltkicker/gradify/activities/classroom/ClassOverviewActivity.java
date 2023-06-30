package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ClassOverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String result = getIntent().getStringExtra("CLASS_ID");
        Log.d("AWEVAWEVAWE", result);
    }
}
