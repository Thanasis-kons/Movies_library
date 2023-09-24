package com.example.thanasis_konstantakis_lab_movie_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    private DeleteMovieAdapter adapter;
    private MovieDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ListView listView = findViewById(R.id.listViewDelete);
        dataSource = new MovieDataSource(this);
        dataSource.open();

        adapter = new DeleteMovieAdapter(this, dataSource.getAllMovies());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = adapter.getItem(position);
            showConfirmationDialog(movie);
        });

        Button btnBack = findViewById(R.id.btnDeleteBack);
        btnBack.setOnClickListener(v -> finish());
    }

    private void showConfirmationDialog(Movie movie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this movie?")
                .setPositiveButton("Delete", (dialog, which) -> deleteMovie(movie.getId()))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteMovie(int movieId) {
        if (dataSource.deleteMovie(movieId)) {
            Toast.makeText(this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
            adapter.removeMovie(movieId);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Failed to delete movie", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
