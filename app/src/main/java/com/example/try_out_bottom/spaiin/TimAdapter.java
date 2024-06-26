package com.example.try_out_bottom.spaiin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.try_out_bottom.R;

import java.util.List;

public class TimAdapter extends RecyclerView.Adapter<TimAdapter.ViewHolder> {
    private List<Tim> teamList;
    private LayoutInflater inflater; // java ke design xml

    // Constructor accepting context and teamList
    TimAdapter(Context context, List<Tim> teamList){
        this.teamList = teamList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TimAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new TimAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimAdapter.ViewHolder holder, int position) {
        Tim team = teamList.get(position);
        holder.bind(team);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.textView);
            teamImage = itemView.findViewById(R.id.imageView);
        }

        public void bind(Tim team) {
            // Bind data to views
            teamName.setText(team.getStrTeam());
            // Assuming you use an image loading library like Glide, load the image here
            Glide.with(itemView.getContext())
                    .load(team.getStrTeamBadge())
                    .into(teamImage);
        }
    }
}
