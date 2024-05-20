package com.example.try_out_bottom.country;

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

public class CoAdapter extends RecyclerView.Adapter<com.example.try_out_bottom.country.CoAdapter.ViewHolder> {
    private List<countriess> teamList;
    private LayoutInflater inflater; // java ke design xml

    // Constructor accepting context and teamList
    CoAdapter(Context context, List<countriess> teamList){
        this.teamList = teamList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public com.example.try_out_bottom.country.CoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new com.example.try_out_bottom.country.CoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.try_out_bottom.country.CoAdapter.ViewHolder holder, int position) {
        countriess team = teamList.get(position);
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

        public void bind(countriess team) {
            // Bind data to views
            teamName.setText(team.getStrLeague());
            // Assuming you use an image loading library like Glide, load the image here
            Glide.with(itemView.getContext())
                    .load(team.getStrBadge())
                    .into(teamImage);
        }
    }
}
