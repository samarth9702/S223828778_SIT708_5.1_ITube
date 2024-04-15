package com.example.itube;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView viewId, viewTask;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        viewId = itemView.findViewById(R.id.playlist_id);
        viewTask = itemView.findViewById(R.id.playlist_url);

    }
}