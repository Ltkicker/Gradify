package com.github.ltkicker.gradify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class logOut extends AppCompatActivity {

    public TextView button_text;
    public ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        button = (ImageButton) findViewById(R.id.back);
        button_text = (TextView) findViewById(R.id.viewprofile);
        button_text = (TextView) findViewById(R.id.myClasses);
        button_text = (TextView) findViewById(R.id.leaderBoard);
        button_text = (TextView) findViewById(R.id.notifications);
        button_text = (TextView) findViewById(R.id.messages);
        button_text = (TextView) findViewById(R.id.settings);
        button_text = (TextView) findViewById(R.id.help);
        button_text = (TextView) findViewById(R.id.aboutUs);
        button_text = (TextView) findViewById(R.id.logout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logOut.this,homepage.class);
                startActivity(intent);
            }
        });
        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(logOut.this,);
            }
        });
    }
}