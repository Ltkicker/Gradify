package com.github.ltkicker.gradify.activities.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    public Button backbutton;

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

        TextView fullname = findViewById(R.id.full_name);
        Log.d("awevawevawe", CacheData.getUsername());
        fullname.setText(CacheData.getFirstName() + " " + CacheData.getLastName());

        backbutton = (Button)findViewById(R.id.img_backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoutActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        button_text2 = (TextView) findViewById(R.id.logout);


        button_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LogoutActivity.this, AuthPortalActivity.class);
                startActivity(intent);
            }
        });
    }
}