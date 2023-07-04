package com.github.ltkicker.gradify.data.grades;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class category1 extends total_percent_parent_category{
        private String parentCategory;
        private String subCategory;
    public category1(String parent_category,String sub_category, int raw_score, double parent_category_percentage) {
        super(sub_category, raw_score, parent_category_percentage);
        this.parentCategory = parent_category; //for parentcategory1 ni siya which is under ani kay ang mga subcategories niya
        this.subCategory = sub_category; //subcategories ni parentcategory1
    }

    public void getSubCatCount(String parent_category, String sub_category){
        FirebaseUtils.SubCategoryCount listener = new FirebaseUtils.SubCategoryCount() {
            @Override
            public void onUpdate(long data) {

                n = data;
            }
        };

        DatabaseReference deRef = FirebaseDatabase.getInstance().getReference().child("NZItQ2M6m_y9IXcgOW7").child("parentcategory").child("parentcategory1").child("percentage");
        DatabaseReference subDeRef = FirebaseDatabase.getInstance().getReference().child("grades").child("NZItQ2M6m_y9IXcgOW7").child("students").child("id").child("parentcategory").child("parentcategory1").child("subcategory");


        deRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long ParentCategoryPercent = snapshot.getValue(Integer.class); // equal ni siya sa parentCategory


                subDeRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listener.onUpdate(snapshot.getChildrenCount());
                        int SumSubcategory = 0;
                        for (DataSnapshot rawScoreSnapshot: snapshot.getChildren()){
                            SumSubcategory += rawScoreSnapshot.getValue(Integer.class);
                        }

                        long totalCategory1ParentPercentage = (SumSubcategory * n) / ParentCategoryPercent;
                        //result ni siya sa parentcategory1
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
