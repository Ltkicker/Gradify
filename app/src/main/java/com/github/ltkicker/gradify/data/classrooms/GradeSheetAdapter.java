package com.github.ltkicker.gradify.data.classrooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.users.User;

import java.util.ArrayList;

public class GradeSheetAdapter extends RecyclerView.Adapter<GradeSheetAdapter.ViewHolder> {
    private final GradeSheetInterface recyclerViewInterface;
    Context context;
    ArrayList<User> students;

    public GradeSheetAdapter(Context context, ArrayList<User> students, GradeSheetInterface recyclerViewInterface) {
        this.context = context;
        this.students = students;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public GradeSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.classlist_row, parent, false);
        return new GradeSheetAdapter.ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeSheetAdapter.ViewHolder holder, int position) {
        holder.fullName.setText(students.get(position).getFirstName() + " " + students.get(position).getLastName());
        holder.idNumber.setText(students.get(position).getIdNumber());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, idNumber;

        public ViewHolder(@NonNull View itemView, GradeSheetInterface recyclerViewInterface) {
            super(itemView);

            fullName = itemView.findViewById(R.id.studentFullName);
            idNumber = itemView.findViewById(R.id.IdNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
