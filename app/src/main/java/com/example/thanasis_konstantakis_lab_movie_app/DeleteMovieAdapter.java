package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DeleteMovieAdapter extends ArrayAdapter<Movie> {
    private final LayoutInflater inflater;

    public DeleteMovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_movie, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.tvTitle);
            holder.categoryTextView = convertView.findViewById(R.id.tvCategory);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = getItem(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.categoryTextView.setText(movie.getCategory());

        return convertView;
    }

    public void removeMovie(int movieId) {
        for (int i = 0; i < getCount(); i++) {
            Movie movie = getItem(i);
            if (movie.getId() == movieId) {
                remove(movie);
                break;
            }
        }
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView categoryTextView;
    }
}
