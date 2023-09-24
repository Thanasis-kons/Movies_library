package com.example.thanasis_konstantakis_lab_movie_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectionMovieAdapter extends RecyclerView.Adapter<ProjectionMovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private OnItemClickListener listener;

    public ProjectionMovieAdapter(List<Movie> movies, OnItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_projection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        // Bind the movie data to the views in the ViewHolder
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare the necessary views and widgets for each item

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize the views and widgets

            // Set up click listener for the item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Movie movie = movies.get(position);
                        listener.onItemClick(movie);
                    }
                }
            });
        }
    }
}
