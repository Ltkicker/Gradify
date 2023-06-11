package com.github.ltkicker.gradify.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.ModelClass;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class GradeSheet extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<ModelClass> arrayList=new ArrayList<>();
    String[] studentList=new String[]{};

    String[] studentNumber = new String[]{};
    int[] imgList = new int[]{};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradesheet);
        //recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);

        for (int i = 0; i < studentList.length; i++) {
            ModelClass modelClass = new ModelClass();
            modelClass.setStudentName(studentList[i]);
            modelClass.setID_number(studentNumber[i]);
            modelClass.setImg(imgList[i]);
            arrayList.add(modelClass);

        }
    }
}