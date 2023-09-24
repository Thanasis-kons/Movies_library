package com.example.thanasis_konstantakis_lab_movie_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProjectionActivity extends AppCompatActivity implements ProjectionMovieAdapter.OnItemClickListener {
    private TextView tvTitle, tvCategory, tvComments, tvWatchedDate;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection);

        tvTitle = findViewById(R.id.tvTitle);
        tvCategory = findViewById(R.id.tvCategory);
        tvComments = findViewById(R.id.tvComments);
        tvWatchedDate = findViewById(R.id.tvWatchedDate);
        btnBack = findViewById(R.id.btnBack);

        int movieId = getIntent().getIntExtra("movie_id", -1);

        if (movieId != -1) {
            MovieDataSource dataSource = new MovieDataSource(this);
            dataSource.open();
            Movie selectedMovie = dataSource.getMovieById(movieId);
            dataSource.close();

            if (selectedMovie != null) {
                displayMovieDetails(selectedMovie);
            } else {
                Toast.makeText(this, "Failed to retrieve movie", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayMovieDetails(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvCategory.setText(movie.getCategory());
        tvComments.setText(movie.getComments());
        tvWatchedDate.setText(movie.getWatchedDate());
    }

    @Override
    public void onItemClick(Movie movie) {

    }
}
