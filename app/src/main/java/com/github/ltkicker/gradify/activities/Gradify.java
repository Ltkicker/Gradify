package com.github.ltkicker.gradify.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.calculator.GradingSystemManager;

import android.content.Intent;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main_loading);
        Intent intent = new Intent(Gradify.this, AuthPortalActivity.class);
        startActivity(intent);
        finish();
        GradingSystemManager cm = new GradingSystemManager("-NY22tZnoVlzaEE-I86T");
        cm.addCategory("Lis1", "Laweva");
        cm.getCategories();
    }
}
