package com.github.ltkicker.gradify.activities.classroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;
import com.github.ltkicker.gradify.data.database.FirebaseUtils;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassGradeSheetActivity extends AppCompatActivity {

    ImageView backbutton;
    private String classroomId = "-NZItQ2M6m_y9IXcgOW7";
    private TableLayout tableLayout;

    TextView classCodetxt;

    DatabaseReference subCatRef = FirebaseDatabase.getInstance().getReference("grades")
            .child(classroomId).child("subcategories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a0_gradesheet_teacher);

        Context context = this;
        backbutton = findViewById(R.id.iconback2);

        classCodetxt = findViewById(R.id.input_subject_code2);
        FirebaseDatabase.getInstance().getReference("classrooms").child(classroomId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                classCodetxt.setText(snapshot.child("code").getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassGradeSheetActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        tableLayout = findViewById(R.id.gradeSheetLayout);

        // ---- HEADERS
        TableRow header = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(5, 5, 5, 5);
        header.setBackgroundColor(ContextCompat.getColor(this, R.color.row1_color));
        header.setLayoutParams(layoutParams);
        tableLayout.addView(header);

        // ID #
        TextView idNumCol = new TextView(this);
        idNumCol.setText("ID #");
        header.addView(idNumCol);

        // Student Name
        TextView studentNameCol = new TextView(this);
        studentNameCol.setText("Student Name");
        header.addView(studentNameCol);

        // Subcategories

        // Fetching all parent categories
        FirebaseUtils.ParentCategoryListener listener = new FirebaseUtils.ParentCategoryListener() {
            @Override
            public void onFetch(ArrayList<ParentCategory> parentCategories) {
                for(ParentCategory parent : parentCategories) {
                    subCatRef.child(parent.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                for(DataSnapshot subCatSnapshot : snapshot.getChildren()) {
                                    SubCategory subCateg = subCatSnapshot.getValue(SubCategory.class);
                                    TextView subCatCol = new TextView(context);
                                    subCatCol.setText(subCateg.getTitle());
                                    header.addView(subCatCol);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    TextView parentCol = new TextView(context);
                    parentCol.setText(parent.getName() + " Avg");
                    header.addView(parentCol);
                }

                TextView finalGradeCol = new TextView(context);
                finalGradeCol.setText("Final Grade");
                header.addView(finalGradeCol);

            }

            @Override
            public void onCancel(String error) {

            }
        };

        FirebaseUtils.getAllParentCategories(classroomId, listener);

    }
}
