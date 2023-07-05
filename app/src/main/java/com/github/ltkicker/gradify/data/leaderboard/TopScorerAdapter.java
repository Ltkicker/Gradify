package com.github.ltkicker.gradify.data.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.data.grades.GradingInstanceData;

import java.util.ArrayList;

public class TopScorerAdapter extends RecyclerView.Adapter<TopScorerAdapter.MyViewHolder> {
    private final TopScorerInterface recyclerViewInterface;
    Context context;
    ArrayList<GradingInstanceData> topScorers;

    public TopScorerAdapter(Context context, ArrayList<GradingInstanceData> subCategories, TopScorerInterface recyclerViewInterface) {
        this.context = context;
        this.topScorers = subCategories;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public TopScorerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topscorers_row, parent, false);
        return new TopScorerAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TopScorerAdapter.MyViewHolder holder, int position) {
        holder.name.setText(topScorers.get(position).getStudentId());
        holder.place.setText(String.valueOf(topScorers.get(position).getPlace()));
    }

    @Override
    public int getItemCount() {
        return topScorers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView place, name;

        public MyViewHolder(@NonNull View itemView, TopScorerInterface recyclerViewInterface) {
            super(itemView);

            place = itemView.findViewById(R.id.lower_rank_num);
            name = itemView.findViewById(R.id.fullname);

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
