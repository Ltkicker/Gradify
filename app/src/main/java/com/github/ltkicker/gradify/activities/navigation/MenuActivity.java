package com.github.ltkicker.gradify.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassGradeSheetActivity;
import com.github.ltkicker.gradify.activities.leaderboard.LeaderboardActivity;
import com.github.ltkicker.gradify.activities.moreinfo.MoreInfoActivity;
import com.github.ltkicker.gradify.data.users.UserCacheData;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!UserCacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity7_main_menu);
        ImageButton classDashboard = findViewById(R.id.menu_addclass);
        classDashboard.setOnClickListener(view -> navigate("CLASSDASH"));

        ImageButton homepage = findViewById(R.id.menu_homepage);
        homepage.setOnClickListener(view -> navigate("HOMEPAGE"));

        ImageButton gradesheet = findViewById(R.id.menu_gradesheet);
        gradesheet.setOnClickListener(view -> navigate("GRADESHEET"));

        ImageButton leaderboard = findViewById(R.id.menu_leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate("LEADERBOARD");
            }
        });

        ImageButton notification = findViewById(R.id.menu_notif);
        notification.setOnClickListener(view -> navigate("NOTIFICATION"));

        ImageButton message = findViewById(R.id.menu_message);
        message.setOnClickListener(view -> navigate("MESSAGE"));

        ImageButton moreinfo = findViewById(R.id.menu_moreinfo);
        moreinfo.setOnClickListener(view -> navigate("MOREINFO"));

    }

    private void navigate(String temp) {
        // replacean rani ug enums, temp raning if else


        if (temp.equals("HOMEPAGE")) {
            Intent intent = new Intent(this, LogoutActivity.class);
            startActivity(intent);
        } else if (temp.equals("CLASSDASH")) {
            Intent intent = new Intent(this, ClassDashboardActivity.class);
            startActivity(intent);
        } else if(temp.equals("GRADESHEET")){
            Intent intent = new Intent(this, ClassGradeSheetActivity.class);
            startActivity(intent);
        } else if(temp.equals("LEADERBOARD")){
            Intent intent = new Intent(MenuActivity.this, LeaderboardActivity.class);
            startActivity(intent);
        } else if(temp.equals("MOREINFO")) {
            Intent intent = new Intent(this, MoreInfoActivity.class);
            startActivity(intent);
        } 
    }
}
