package com.github.ltkicker.gradify.data.grades;
import android.util.Log;
import androidx.annotation.NonNull;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class total_percent_parent_category extends parent_category {
    long n;
    DatabaseReference deRef =  FirebaseDatabase.getInstance().getReference("Grades").child("parentcategory");
    public total_percent_parent_category(String sub_category, int raw_score, double parent_category_pertage) {
        super(sub_category, raw_score, parent_category_pertage);

    }

    public void getSubCatCount() {

        FirebaseUtils.SubCategoryCount listener = new FirebaseUtils.SubCategoryCount() {
            @Override
            public void onUpdate(long data) {
                n = data;
            }
        };
        deRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //childSnapshot - parentCategory 1-4
                //getChildren - subcategory
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    listener.onUpdate(childSnapshot.getChildrenCount());
                    int SumSubcategory = 0;
                    int ParentPercent = 0;
                    for(DataSnapshot rawScoreSnapshot: childSnapshot.getChildren()){
                        SumSubcategory += rawScoreSnapshot.getValue(Integer.class);
                    }
                    for(DataSnapshot parentPercentSnapshot: childSnapshot.getChildren()){
                        ParentPercent = parentPercentSnapshot.getValue(Integer.class);
                    }

                    int totalParentPercentage = (int) ((SumSubcategory/n) * ParentPercent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Property", error.toString());
            }
        });

    }



}
