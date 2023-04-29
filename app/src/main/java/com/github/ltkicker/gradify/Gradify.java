package com.github.ltkicker.gradify;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setContentView(R.layout.layout_loginportal);

    }
}