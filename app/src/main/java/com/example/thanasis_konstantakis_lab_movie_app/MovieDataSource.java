package com.example.thanasis_konstantakis_lab_movie_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieDataSource {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    private String[] allColumns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_TITLE,
            DbHelper.COLUMN_CATEGORY,
            DbHelper.COLUMN_COMMENTS,
            DbHelper.COLUMN_WATCHED_DATE
    };

    public MovieDataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Movie createMovie(String title, String category, String comments, String watchedDate) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, title);
        values.put(DbHelper.COLUMN_CATEGORY, category);
        values.put(DbHelper.COLUMN_COMMENTS, comments);
        values.put(DbHelper.COLUMN_WATCHED_DATE, watchedDate);

        long insertId = database.insert(DbHelper.TABLE_MOVIES, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_MOVIES, allColumns,
                DbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();
        Movie newMovie = cursorToMovie(cursor);
        cursor.close();

        return newMovie;
    }

    public boolean updateMovie(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, movie.getTitle());
        values.put(DbHelper.COLUMN_CATEGORY, movie.getCategory());
        values.put(DbHelper.COLUMN_COMMENTS, movie.getComments());
        values.put(DbHelper.COLUMN_WATCHED_DATE, movie.getWatchedDate());

        int rowsAffected = database.update(DbHelper.TABLE_MOVIES, values,
                DbHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(movie.getId())});

        return rowsAffected > 0;
    }

    public final class MovieEntry {
        public static final String TABLE_NAME = "movies";
        public static final String _ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_COMMENTS = "comments";
        public static final String COLUMN_WATCHED_DATE = "watched_date";

        private MovieEntry() {
            // Private constructor to prevent instantiation
        }
    }

    public boolean deleteMovie(int movieId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsAffected = database.delete(MovieEntry.TABLE_NAME, MovieEntry._ID + " = ?",
                new String[]{String.valueOf(movieId)});
        return rowsAffected > 0;
    }



    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_MOVIES, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Movie movie = cursorToMovie(cursor);
            movies.add(movie);
            cursor.moveToNext();
        }

        cursor.close();

        return movies;
    }

    public Movie getMovieById(int movieId) {
        Cursor cursor = database.query(DbHelper.TABLE_MOVIES, allColumns,
                DbHelper.COLUMN_ID + " = " + movieId, null, null, null, null);

        cursor.moveToFirst();
        Movie movie = cursorToMovie(cursor);
        cursor.close();

        return movie;
    }



    @SuppressLint("Range")
    private Movie cursorToMovie(Cursor cursor) {
        Movie movie = new Movie();
        movie.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_TITLE)));
        movie.setCategory(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_CATEGORY)));
        movie.setComments(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_COMMENTS)));
        movie.setWatchedDate(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_WATCHED_DATE)));

        return movie;
    }
}
