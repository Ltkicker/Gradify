package com.github.ltkicker.gradify.data.classrooms;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;

import java.util.ArrayList;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Classroom> classrooms;

    public ClassListAdapter(Context context, ArrayList<Classroom> classrooms) {
        this.context = context;
        this.classrooms = classrooms;
    }

    @NonNull
    @Override
    public ClassListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.classlist_row, parent, false);
        return new ClassListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassListAdapter.MyViewHolder holder, int position) {
        holder.classCode.setText(classrooms.get(position).getCode());
        holder.classDesc.setText(classrooms.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView classCode, classDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            classCode = itemView.findViewById(R.id.classCode);
            classDesc = itemView.findViewById(R.id.classDesc);

        }
    }
}
