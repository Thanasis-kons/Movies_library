package com.example.thanasis_konstantakis_lab_movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnView = findViewById(R.id.btnView);
        Button btnEditView = findViewById(R.id.btnEditview);
        Button btnDeleteMenu = findViewById(R.id.btnDeleteMenu);

        btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        });

        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            startActivity(intent);
        });





        btnEditView.setOnClickListener(v -> {
            Intent intent =  new Intent(MainActivity.this, EditViewActivity.class);
            startActivity(intent);
        });

        btnDeleteMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(intent);
        });
    }
}
