package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private final Context context;
    private final ArrayList<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        super(context, 0, movieList);
        this.context = context;
        this.movieList = (ArrayList<Movie>) movieList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        // Get the movie at the current position
        final Movie currentMovie = movieList.get(position);

        // Set the title and category of the movie to the corresponding TextViews
        TextView tvTitle = listItemView.findViewById(R.id.tvTitle);
        TextView tvCategory = listItemView.findViewById(R.id.tvCategory);
        TextView tvComments = listItemView.findViewById(R.id.tvComments);
        TextView tvWatchedDate = listItemView.findViewById(R.id.tvWatchedDate);



        // Check if TextView objects are null before setting the text
        if (tvTitle != null) {
            tvTitle.setText(currentMovie.getTitle());
        }

        if (tvCategory != null) {
            tvCategory.setText(currentMovie.getCategory());
        }

        if (tvComments != null) {
            tvComments.setText(currentMovie.getComments());
        }

        if (tvWatchedDate != null) {
            tvWatchedDate.setText(currentMovie.getWatchedDate());
        }



        listItemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("movie_id", currentMovie.getId());
            context.startActivity(intent);
        });


        return listItemView;
    }


}
