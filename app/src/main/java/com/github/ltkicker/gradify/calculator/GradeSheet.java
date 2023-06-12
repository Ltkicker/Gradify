package com.github.ltkicker.gradify.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.ModelClass;
import com.github.ltkicker.gradify.data.StudentAdapter;
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
    ArrayList<ModelClass> searchList;
    String[] studentList=new String[]{};

    String[] studentNumber = new String[]{};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradesheet);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.clearFocus();
        for (int i = 0; i < studentList.length; i++) {
            ModelClass modelClass = new ModelClass();
            modelClass.setStudentName(studentList[i]);
            modelClass.setID_number(studentNumber[i]);
            arrayList.add(modelClass);

        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GradeSheet.this);
        recyclerView.setLayoutManager(layoutManager);


        StudentAdapter studentAdapter = new StudentAdapter(GradeSheet.this,arrayList);
        recyclerView.setAdapter(studentAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();

                if (query.length()>0){
                    for (int i = 0; i <arrayList.size() ;i++){
                        if (arrayList.get(i).getStudentName().toUpperCase().contains(query.toUpperCase())|| arrayList.get(i).getID_number().toUpperCase().contains(query.toUpperCase())){

                            ModelClass modelClass = new ModelClass();
                            modelClass.setStudentName(arrayList.get(i).getStudentName());
                            modelClass.setID_number(arrayList.get(i).getID_number());

                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GradeSheet.this);
                    recyclerView.setLayoutManager(layoutManager);


                    StudentAdapter studentAdapter = new StudentAdapter(GradeSheet.this,searchList);
                    recyclerView.setAdapter(studentAdapter);
                }
                else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GradeSheet.this);
                    recyclerView.setLayoutManager(layoutManager);


                    StudentAdapter studentAdapter = new StudentAdapter(GradeSheet.this,arrayList);
                    recyclerView.setAdapter(studentAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();

                if (newText.length()>0){
                    for (int i = 0; i <arrayList.size() ;i++){
                        if (arrayList.get(i).getStudentName().toUpperCase().contains(newText.toUpperCase()) || arrayList.get(i).getID_number().toUpperCase().contains(newText.toUpperCase())){

                            ModelClass modelClass = new ModelClass();
                            modelClass.setStudentName(arrayList.get(i).getStudentName());
                            modelClass.setID_number(arrayList.get(i).getID_number());

                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GradeSheet.this);
                    recyclerView.setLayoutManager(layoutManager);


                    StudentAdapter studentAdapter = new StudentAdapter(GradeSheet.this,searchList);
                    recyclerView.setAdapter(studentAdapter);
                }
                else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GradeSheet.this);
                    recyclerView.setLayoutManager(layoutManager);


                    StudentAdapter studentAdapter = new StudentAdapter(GradeSheet.this,arrayList);
                    recyclerView.setAdapter(studentAdapter);
                }
                return false;
            }
        });
    }
}