package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    private EditText editTitle, editCategory, editComments, editWatchedDate;
    private Button btnUpdate, btnBack;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTitle = findViewById(R.id.editTitle);
        editCategory = findViewById(R.id.editCategory);
        editComments = findViewById(R.id.editComments);
        editWatchedDate = findViewById(R.id.editWatchedDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnEditBack);

        // Retrieve the movie ID from the intent
        int movieId = getIntent().getIntExtra("movie_id", -1);

        if (movieId != -1) {
            MovieDataSource dataSource = new MovieDataSource(this);
            dataSource.open();

            movie = dataSource.getMovieById(movieId); // Fetch the movie details from the database using the ID

            if (movie != null) {
                displayMovieDetails(movie);
            } else {
                Toast.makeText(this, "Failed to retrieve movie", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMovieDetails();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayMovieDetails(Movie movie) {
        editTitle.setText(movie.getTitle());
        editCategory.setText(movie.getCategory());
        editComments.setText(movie.getComments());
        editWatchedDate.setText(movie.getWatchedDate());
    }

    private void updateMovieDetails() {
        String updatedTitle = editTitle.getText().toString();
        String updatedCategory = editCategory.getText().toString();
        String updatedComments = editComments.getText().toString();
        String updatedWatchedDate = editWatchedDate.getText().toString();

        // Update the movie object with new values
        movie.setTitle(updatedTitle);
        movie.setCategory(updatedCategory);
        movie.setComments(updatedComments);
        movie.setWatchedDate(updatedWatchedDate);

        // Update the movie in the database
        MovieDataSource dataSource = new MovieDataSource(this);
        dataSource.open();
        boolean updated = dataSource.updateMovie(movie);
        dataSource.close();

        if (updated) {
            // Return the updated movie object to the previous activity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updated_movie", movie);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Toast.makeText(this, "Failed to update movie", Toast.LENGTH_SHORT).show();
        }
    }
}
