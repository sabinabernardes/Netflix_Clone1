package com.binapp.netflix_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;


import com.binapp.netflix_clone.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieHolder.MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerview = findViewById(R.id.reclycler_view_main);

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            Movie movie = new Movie();
            movie.setCoverUrl("abc"+ i);
            movies.add(movie);

        }
       // mainAdapter = new MainAdapter(movies);
        recyclerview.setLayoutManager(
                new LinearLayoutManager
                        (this,RecyclerView.VERTICAL,false));
        recyclerview.setAdapter( mainAdapter);
    }
    private class MovieHolder extends RecyclerView.ViewHolder {

        private final TextView textViewURL;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            textViewURL = itemView.findViewById(R.id.textView_url);



        }
    private class MainAdapter extends RecyclerView.Adapter<MovieHolder>{

            private final List<Movie> movies;

        public MainAdapter(List<Movie> movies) {
            this.movies = movies;
        }

        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieHolder(getLayoutInflater()
                    .inflate(R.layout.movie_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

            Movie movie  = movies.get(position);
            holder.textViewURL.setText(movie.getCoverUrl());
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }

    }


}