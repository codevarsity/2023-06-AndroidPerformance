package com.example.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int pos) {
        final Movie movie = movies.get(pos);
        Log.i("MoviesAdapter", movie.getName());
        moviesViewHolder.name.setText(movie.getName());


        moviesViewHolder.advertisementImageView.setImageResource(movie.getAdvertisement());

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MoviesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView advertisementImageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            advertisementImageView = itemView.findViewById(R.id.poster);
        }
    }
}
