package com.example.thanasis_konstantakis_lab_movie_app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String category;
    private String comments;
    private String watchedDate;

    public Movie() {
    }

    public Movie(String title, String category, String comments, String watchedDate) {
        this.title = title;
        this.category = category;
        this.comments = comments;
        this.watchedDate = watchedDate;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getWatchedDate() {
        return watchedDate;
    }

    public void setWatchedDate(String watchedDate) {
        this.watchedDate = watchedDate;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }

    // Parcelable implementation

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        category = in.readString();
        comments = in.readString();
        watchedDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(comments);
        dest.writeString(watchedDate);
    }
}
