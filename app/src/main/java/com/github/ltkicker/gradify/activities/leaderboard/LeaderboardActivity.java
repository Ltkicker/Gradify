package com.github.ltkicker.gradify.activities.leaderboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.leaderboard.GradeSubCategoryAdapter;
import com.github.ltkicker.gradify.data.leaderboard.GradeSubCategoryInterface;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity implements GradeSubCategoryInterface {

    private RecyclerView subCategList;
    private ArrayList<SubCategory> subCategories;
    private GradeSubCategoryAdapter adapter;

    private DatabaseReference dRef;

    String keyReference;
    private ArrayList<String> subKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b0_leaderboard_teacher);
        subKey = new ArrayList<>();

        // Kato naning mag change2 ug category pero diari rako taman kay bungkag ang front end ani
        keyReference = "parentcategory4";

        subCategList = findViewById(R.id.subCategList);
        subCategList.setLayoutManager(new LinearLayoutManager(this));
        subCategList.setBackgroundResource(android.R.color.transparent);
        subCategories = new ArrayList<>();
        adapter = new GradeSubCategoryAdapter(this, subCategories, this);
        subCategList.setAdapter(adapter);

        dRef = FirebaseDatabase.getInstance().getReference("grades").child("NZItQ2M6m_y9IXcgOW7").child("subcategories");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    subCategories.clear();
                    subKey.clear();
                    for(DataSnapshot subSnapshot : snapshot.child(keyReference).getChildren()) {
                        subCategories.add(subSnapshot.getValue(SubCategory.class));
                        subKey.add(subSnapshot.getKey());

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, LeaderboardTopScorers.class);
        intent.putExtra("PARENTCATEGORYID", keyReference);
        intent.putExtra("SUBCATEGORY", subCategories.get(position));
        intent.putExtra("SUBCATEGORYID", subKey.get(position));
        startActivity(intent);
    }
}
