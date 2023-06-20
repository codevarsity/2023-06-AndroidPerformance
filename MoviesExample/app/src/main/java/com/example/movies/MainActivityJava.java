package com.example.movies;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import leakcanary.LeakCanary;

public class MainActivityJava extends AppCompatActivity {

    Movie movie = new Movie(0, "Top Gun", R.drawable.topgun, Genre.ACION);
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies.add(new  Movie(0, "Top Gun", R.drawable.topgun, Genre.ACION));
        movies.add(new Movie(1, "The Lego Movie", R.drawable.lego, Genre.FAMILY));
        movies.add(new Movie(2, "Frozen", R.drawable.frozen, Genre.FAMILY));
        movies.add(new Movie(3, "Up", R.drawable.up, Genre.FAMILY));
        movies.add(new Movie(4, "Me, Myself & Irene", R.drawable.memyself, Genre.COMEDY));
        movies.add(new Movie(5, "He Got Game", R.drawable.hegotgame, Genre.SPORTS));
        movies.add(new Movie(6, "Batman", R.drawable.batman, Genre.ACION));
        movies.add(new Movie(7, "Mighty Ducks", R.drawable.mightyducks, Genre.SPORTS));
        movies.add(new Movie(8, "Billy Madison", R.drawable.billymadison, Genre.COMEDY));
        movies.add(new Movie(9, "Bohemian Rhapsody", R.drawable.bohemian, Genre.DRAMA));
        movies.add(new Movie(10, "Trolls", R.drawable.trolls, Genre.FAMILY));
        movies.add(new Movie(11, "The Mask", R.drawable.themask, Genre.COMEDY));
        movies.add(new Movie(12, "Catch Me If You Can", R.drawable.catchmeifyoucan, Genre.DRAMA));
        movies.add(new Movie(13, "Titanic", R.drawable.titanic, Genre.DRAMA));
        movies.add(new Movie(14, "The Equalizer", R.drawable.equalizer, Genre.DRAMA));
        movies.add(new Movie(15, "Star Wars", R.drawable.starwars, Genre.SCIFI));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerView.setAdapter(new MoviesAdapter(this, movies));

    }
}
