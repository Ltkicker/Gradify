package com.github.ltkicker.gradify.data;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyHolder> {
    Context context;
    ArrayList<ModelClass>arrayList;
    LayoutInflater layoutInflater;

    public StudentAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_itemfileforgradesheet,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyHolder holder, int position) {

        holder.StudentName.setText(arrayList.get(position).getStudentName());
        holder.IDNum.setText(arrayList.get(position).getID_number());
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        TextView StudentName,IDNum;
        public MyHolder (@NonNull View itemView){

            super(itemView);
            StudentName = itemView.findViewById(R.id.txt);
            IDNum = itemView.findViewById(R.id.txt2);

        }
    }
}
