package com.github.ltkicker.gradify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassGradeSheetActivity;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;

public class MainMenu extends AppCompatActivity {
    private ImageButton menu_homepage;
    private ImageButton menu_addclass;
    private ImageButton menu_gradesheet;
    private ImageButton menu_leaderboard;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity7_main_menu);

        menu_homepage = (ImageButton) findViewById(R.id.menu_homepage);
        menu_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        setContentView(R.layout.activity7_main_menu);

        menu_addclass = (ImageButton) findViewById(R.id.menu_addclass);
        menu_addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Classes();
            }
        });

        setContentView(R.layout.activity7_main_menu);

        menu_gradesheet = (ImageButton) findViewById(R.id.menu_gradesheet);
        menu_gradesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Grades();
            }
        });

    }
    public void Logout(){
        Intent intent = new Intent(MainMenu.this, LogOut.class);
        startActivity(intent);
    }
    public void Classes(){
        Intent intent = new Intent(MainMenu.this, ClassDashboardActivity.class);
        startActivity(intent);
    }

    public void Grades(){
        Intent intent = new Intent(MainMenu.this, ClassGradeSheetActivity.class);
        startActivity(intent);
    }


}
