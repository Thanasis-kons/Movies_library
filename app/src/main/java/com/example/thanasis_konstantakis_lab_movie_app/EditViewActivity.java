package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditViewActivity extends AppCompatActivity {

    private ListView listView;
    private MovieDataSource dataSource;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editview);

        listView = findViewById(R.id.listViewMovies);
        btnBack = findViewById(R.id.btnViewBack);

        dataSource = new MovieDataSource(this);
        dataSource.open();

        List<Movie> movies = dataSource.getAllMovies();
        MovieAdapter adapter = new MovieAdapter(this, movies);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movies.get(position);

                Intent intent = new Intent(EditViewActivity.this, EditActivity.class);
                intent.putExtra("selected_movie", selectedMovie);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
