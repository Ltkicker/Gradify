package com.github.ltkicker.gradify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class logOutToHome extends AppCompatActivity {

    public ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        button = (ImageButton) findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logOutToHome.this,homepage.class);
                startActivity(intent);
            }
        });
    }
}