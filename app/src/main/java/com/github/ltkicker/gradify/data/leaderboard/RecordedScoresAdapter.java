package com.github.ltkicker.gradify.data.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;

import java.util.ArrayList;

public class RecordedScoresAdapter extends RecyclerView.Adapter<RecordedScoresAdapter.MyViewHolder> {
    private final RecordedScoresInterface recyclerViewInterface;
    Context context;
    ArrayList<SubCategory> subCategories;

    public RecordedScoresAdapter(Context context, ArrayList<SubCategory> subCategories, RecordedScoresInterface recyclerViewInterface) {
        this.context = context;
        this.subCategories = subCategories;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecordedScoresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.record_scores_row, parent, false);
        return new RecordedScoresAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordedScoresAdapter.MyViewHolder holder, int position) {
        holder.name.setText(subCategories.get(position).getName());
        holder.date.setText(subCategories.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, name;

        public MyViewHolder(@NonNull View itemView, RecordedScoresInterface recyclerViewInterface) {
            super(itemView);

            date = itemView.findViewById(R.id.quiz_date);
            name = itemView.findViewById(R.id.quiz_text);

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
