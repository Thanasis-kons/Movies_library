package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etCategory;
    private EditText etComments;
    private EditText etWatchedDate;
    private Button btnSave;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etTitle = findViewById(R.id.etTitle);
        etCategory = findViewById(R.id.etCategory);
        etComments = findViewById(R.id.etComments);
        etWatchedDate = findViewById(R.id.etWatchedDate);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnCreateBack);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String category = etCategory.getText().toString();
                String comments = etComments.getText().toString();
                String watchedDate = etWatchedDate.getText().toString();

                MovieDataSource dataSource = new MovieDataSource(CreateActivity.this);
                dataSource.open();
                Movie newMovie = dataSource.createMovie(title, category, comments, watchedDate);
                dataSource.close();

                if (newMovie != null) {
                    Toast.makeText(CreateActivity.this, "Movie saved successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateActivity.this, MovieDetailsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateActivity.this, "Failed to save movie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
