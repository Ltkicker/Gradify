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
    DatabaseReference deRef =  FirebaseDatabase.getInstance().getReference("grades").child("NZItQ2M6m_y9IXcgOW7").child("students").child("parentcategory");
    DatabaseReference subDeRef =  FirebaseDatabase.getInstance().getReference("grades").child("NZItQ2M6m_y9IXcgOW7").child("students").child("parentcategory").child("subcategory");
    public total_percent_parent_category(String sub_category, int raw_score, double parent_category_percentage) {
        super(sub_category, raw_score, parent_category_percentage);

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
                for (DataSnapshot secondSnapshot : snapshot.getChildren()) {

                    long ParentPercent = secondSnapshot.getValue(Integer.class);
                    String key = secondSnapshot.getKey();

                    subDeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot secondSnapshot : snapshot.getChildren()) {
                                if (secondSnapshot.getKey().equals(key)) {
                                    listener.onUpdate(secondSnapshot.getChildrenCount());
                                    int SumSubcategory = 0;
                                    for(DataSnapshot rawScoreSnapshot : secondSnapshot.getChildren()) {
                                        SumSubcategory += rawScoreSnapshot.getValue(Integer.class);
                                    }
                                    long totalParentPercentage = ((SumSubcategory/n) * ParentPercent);
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Property", error.toString());
            }
        });


    }



}
