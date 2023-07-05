package com.github.ltkicker.gradify.activities.moreinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.users.CacheData;

public class MoreInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_c_moreicon_teacher_student);

//        Button homepage = findViewById(R.id.img_home_button5);
//        homepage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MoreInfoActivity.this, MenuActivity.class);
//            }
//        });
    }
}


