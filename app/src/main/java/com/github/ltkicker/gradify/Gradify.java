package com.github.ltkicker.gradify;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.data.ClassroomHandler;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Sets the activity to loading screen
        setContentView(R.layout.activity_loading);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Sets the activity to authentication area
        setContentView(R.layout.activity_authportal);

    }
}