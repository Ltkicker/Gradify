package com.github.ltkicker.gradify.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;

import android.content.Intent;

public class LoadingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpage);
        Intent intent = new Intent(LoadingActivity.this, AuthPortalActivity.class);
        startActivity(intent);

    }
}
