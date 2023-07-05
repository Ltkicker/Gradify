package com.github.ltkicker.gradify.data.database;

import androidx.annotation.NonNull;

import com.github.ltkicker.gradify.data.classrooms.Classroom;
import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseUtils {
    static DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
    static boolean hasData;
    static ArrayList<Classroom> classesOfUsers;

    // Utility method that checks if path in database exist


    public interface OnPathExistsListener {
        void onPathExists(boolean exists);
    }

    public interface ClassListListener {
        void onUpdate(ArrayList<Classroom> classList);
    }

    public interface SubCategoryCount {
        void onUpdate(long data);
    }


    public interface ClassListByIdListener {
        void onUpdate(ArrayList<String> classListById);
    }

    public interface GetClassroomNameListener {
        void onFetch(String classroomById);
    }

    public interface ParentCategoryListener {
        void onFetch(ArrayList<ParentCategory> parentCategories);
        void onCancel(String error);
    }

    public static void getAllParentCategories(String classroomId, ParentCategoryListener listener) {
        FirebaseDatabase.getInstance().getReference("grades").child(classroomId).child("parentcategory")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            ArrayList<ParentCategory> values = new ArrayList<>();
                            for(DataSnapshot child : snapshot.getChildren()) {
                                values.add(new ParentCategory(child.getKey(), child.child("percentage").getValue(Double.class)));
                            }
                            listener.onFetch(values);
                        } else {
                            listener.onCancel("Something went wrong");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
        });
    }

//    public static void getAllSubCategories(String classroomId, SubCategoriesListener listener) {
//        FirebaseDatabase.getInstance().getReference("grades").child(classroomId).child("subcategories")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()) {
//
//                            for(DataSnapshot child : snapshot.getChildren()) {
//                                values.put(new ParentCategory(child.getKey(), child.child("percentage").getValue(Double.class)));
//                            }
//                            listener.onFetch(values);
//                        } else {
//                            listener.onCancel("Something went wrong");
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }


    public static ArrayList<String> getClassListOfUsersById() {
        ArrayList<String> result = new ArrayList<>();
        DatabaseReference tempDataRef;
        if(CacheData.isTeacher()) {
            tempDataRef = mDataRef.child("users").child(CacheData.getUsername()).child("classrooms").child("asTeacher");

        } else {
            tempDataRef = mDataRef.child("users").child(CacheData.getUsername()).child("classrooms").child("asStudent");

        }
        tempDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot value : snapshot.getChildren()) {
                        result.add(value.getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return result;
    }



    public static void init() {

    }
}
