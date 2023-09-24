package com.example.thanasis_konstantakis_lab_movie_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ListView listViewMovieDetails = findViewById(R.id.listViewMovieDetails);
        Button btnBack = findViewById(R.id.btnBack);

        // Create an instance of the MovieDataSource and open the database
        MovieDataSource dataSource = new MovieDataSource(this);
        dataSource.open();

        // Get all movies from the database
        List<Movie> movieList = dataSource.getAllMovies();

        // Create an instance of the MovieDetailsAdapter and set it as the adapter for the ListView
        MovieDetailsAdapter adapter = new MovieDetailsAdapter(this, movieList);
        listViewMovieDetails.setAdapter(adapter);

        // Close the database
        dataSource.close();

        btnBack.setOnClickListener(v -> {
            finish(); // Finish the activity and go back to the previous activity
        });
    }
}
