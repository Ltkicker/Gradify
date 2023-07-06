package com.github.ltkicker.gradify.activities.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.calculator.StudentGradeManager;
import com.github.ltkicker.gradify.calculator.UniversityGrade;
import com.github.ltkicker.gradify.calculator.listeners.GradesRefresherListener;
import com.github.ltkicker.gradify.data.grades.UserStandingData;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

import com.github.ltkicker.gradify.activities.classroom.ClassDashboardActivity;
import com.github.ltkicker.gradify.activities.classroom.ClassOverviewActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentOverallStandingActivity extends AppCompatActivity {
    private ArrayList<ParentCategory> parentCategories;
    private DatabaseReference dRef;

    private String classroomId = "NZItQ2M6m_y9IXcgOW7";
    TextView bdCategory1;
    TextView bdCategory2;
    TextView bdCategory3;
    TextView bdCategory4;
    TextView bdPercent1, bdPercent2, bdPercent3, bdPercent4;
    TextView bdPercentCurrent1, bdPercentCurrent2, bdPercentCurrent3, bdPercentCurrent4;
    Button backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_overall_standing_student);

        dRef = FirebaseDatabase.getInstance().getReference("grades").child(classroomId);

        bdCategory1 = findViewById(R.id.parentCategory1);
        bdCategory2 = findViewById(R.id.parentCategory2);
        bdCategory3 = findViewById(R.id.parentCategory3);
        bdCategory4 = findViewById(R.id.parentCategory4);

        bdPercent1 = findViewById(R.id.breakdown_percentage);
        bdPercent2 = findViewById(R.id.breakdown_percentage2);
        bdPercent3 = findViewById(R.id.breakdown_percentage3);
        bdPercent4 = findViewById(R.id.breakdown_percentage4);


        bdPercentCurrent1 = findViewById(R.id.breakdown_percentage_current1);
        bdPercentCurrent2 = findViewById(R.id.breakdown_percentage_current2);
        bdPercentCurrent3 = findViewById(R.id.breakdown_percentage_current3);
        bdPercentCurrent4 = findViewById(R.id.breakdown_percentage_current4);

        String studentId = "2020-0001";

        GradesRefresherListener listener = new GradesRefresherListener() {
            @Override
            public void onRefresh(HashMap<String, UserStandingData> data) {
                for (ParentCategory parent : data.get(studentId).getBreakdown().keySet()) {
                    Double percentBr = parent.getPercentage() * 100;
                    Double percent = data.get(studentId).getBreakdown().get(parent);
                    DecimalFormat decimalFormat = new DecimalFormat("#.00");
                    DecimalFormat decimalFormat2 = new DecimalFormat("#");
                    String formattedNumber = decimalFormat.format(percent) + "%";
                    String formattedNumber2 = decimalFormat2.format(percentBr) + "%";

                    if (parent.getKey().equalsIgnoreCase("parentCategory1")) {
                        bdCategory1.setText(parent.getName());
                        bdPercent1.setText(formattedNumber2);
                        bdPercentCurrent1.setText(formattedNumber);
                    } else if (parent.getKey().equalsIgnoreCase("parentcategory2")) {
                        bdCategory2.setText(parent.getName());
                        bdPercent2.setText(formattedNumber2);
                        bdPercentCurrent2.setText(formattedNumber);
                    } else if (parent.getKey().equalsIgnoreCase("parentcategory3")) {
                        bdCategory3.setText(parent.getName());
                        bdPercent3.setText(formattedNumber2);
                        bdPercentCurrent3.setText(formattedNumber);
                    } else if (parent.getKey().equalsIgnoreCase("parentcategory4")) {
                        bdCategory4.setText(parent.getName());
                        bdPercent4.setText(formattedNumber2);
                        bdPercentCurrent4.setText(formattedNumber);
                    }
                }
                double finalGrade = data.get(studentId).getFinalGrade();
                TextView finalGradetxt = findViewById(R.id.overall_percentage);
                finalGradetxt.setText(String.valueOf(finalGrade));
                TextView uniGradetxt = findViewById(R.id.value_equivalent_grade);
                Double uniGrade = UniversityGrade.convert(finalGrade);
                uniGradetxt.setText(String.valueOf(uniGrade));
            }
        };
        StudentGradeManager.getGrades(listener);
   //     FirebaseUtils.getAllParentCategories("NZItQ2M6m_y9IXcgOW7", listener);

        }
    }

