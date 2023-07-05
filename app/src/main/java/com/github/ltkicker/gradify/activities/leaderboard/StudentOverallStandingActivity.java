package com.github.ltkicker.gradify.activities.leaderboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;

import java.util.ArrayList;

public class StudentOverallStandingActivity extends AppCompatActivity {

    private ArrayList<ParentCategory> parentCategories;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_overall_standing_student);

        TextView bdCategory1 = findViewById(R.id.parentCategory1);
        TextView bdCategory2 = findViewById(R.id.parentCategory2);
//        TextView bdCategory3 = findViewById(R.id.parentCategory3);
//        TextView bdCategory4 = findViewById(R.id.parentCategory4);

        TextView bdPercent1 = findViewById(R.id.breakdown_percentage);
        TextView bdPercent2 = findViewById(R.id.breakdown_percentage2);
        TextView bdPercent3 = findViewById(R.id.breakdown_percentage3);
        TextView bdPercent4 = findViewById(R.id.breakdown_percentage4);

        FirebaseUtils.ParentCategoryListener listener = new FirebaseUtils.ParentCategoryListener() {
            @Override
            public void onFetch(ArrayList<ParentCategory> parentCategories) {
                for(ParentCategory parent : parentCategories) {
                }
            }

            @Override
            public void onCancel(String error) {

            }
        };
        FirebaseUtils.getAllParentCategories("NZItQ2M6m_y9IXcgOW7", listener);

    }
}
