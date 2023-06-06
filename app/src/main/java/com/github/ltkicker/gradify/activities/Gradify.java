package com.github.ltkicker.gradify.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;

import android.content.Intent;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpage);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Gradify.this, AuthPortalActivity.class);
        startActivity(intent);
        finish();
    }
}
