package com.github.ltkicker.gradify.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;

import android.content.Intent;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Intent intent = new Intent(Gradify.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
