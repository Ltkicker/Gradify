package com.github.ltkicker.gradify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.activities.authentication.StudentLoginActivity;
import com.github.ltkicker.gradify.activities.authentication.TeacherLoginActivity;

public class Gradify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Sets the activity to loading screen
        //setContentView(R.layout.activity_loading);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setContentView(R.layout.activity_authportal);
        ImageButton studentPortal = findViewById(R.id.imgstudent);
        studentPortal.setOnClickListener(v -> changeView("student"));

        ImageButton teacherPortal = findViewById(R.id.imgteacher);
        teacherPortal.setOnClickListener(v -> changeView("teacher"));


    }

    private void changeView(String nav) {
        switch(nav) {
            case "student":
                Intent studentIntent = new Intent(Gradify.this, StudentLoginActivity.class);
                startActivity(studentIntent);
                break;
            case "teacher":
                Intent teacherIntent = new Intent(Gradify.this, TeacherLoginActivity.class);
                startActivity(teacherIntent);
                break;
            default:
                break;
        }
    }
}