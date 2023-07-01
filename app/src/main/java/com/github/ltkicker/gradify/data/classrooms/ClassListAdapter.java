package com.github.ltkicker.gradify.data.classrooms;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;

import java.util.ArrayList;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.MyViewHolder> {
    private final ClassListInterface recyclerViewInterface;
    Context context;
    ArrayList<Classroom> classrooms;

    public ClassListAdapter(Context context, ArrayList<Classroom> classrooms, ClassListInterface recyclerViewInterface) {
        this.context = context;
        this.classrooms = classrooms;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ClassListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.classlist_row, parent, false);
        return new ClassListAdapter.MyViewHolder(view, recyclerViewInterface);
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

        public MyViewHolder(@NonNull View itemView, ClassListInterface recyclerViewInterface) {
            super(itemView);

            classCode = itemView.findViewById(R.id.classCode);
            classDesc = itemView.findViewById(R.id.classDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        Log.d("AWEVAWEVAWE", "WORKING POS");
                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
