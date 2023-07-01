package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;

public class ClassGradeSheetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_gradesheet_teacher);
    }
}
