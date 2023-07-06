package com.github.ltkicker.gradify.activities.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity {
    public TextView button_text;
    public TextView button_text2;
    public ImageView button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity8_if_homebuttonicon_goto_logout);

        button = (ImageView) findViewById(R.id.backlog_out_ground);
        button_text = (TextView) findViewById(R.id.Myclasses);
        button_text = (TextView) findViewById(R.id.txtleaderboards_string);
        button_text = (TextView) findViewById(R.id.notifications);
        button_text = (TextView) findViewById(R.id.messages);
        button_text = (TextView) findViewById(R.id.settings);
        button_text = (TextView) findViewById(R.id.help);
        button_text = (TextView) findViewById(R.id.aboutUs);

        ImageView logout1 = findViewById(R.id.logouticn);

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LogoutActivity.this, AuthPortalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView logout2 = findViewById(R.id.logout);

        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LogoutActivity.this, AuthPortalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoutActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}