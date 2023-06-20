package com.example.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieView extends FrameLayout {
    private TextView name;
    private ImageView advertisement;

    public MovieView(Context context) {
        this(context, null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.view_movie, this, false);
        name = view.findViewById(R.id.name);
        advertisement = view.findViewById(R.id.poster);
        addView(view);
    }


    public void setName(String movieName) {
        if (name != null) {
            name.setText(movieName);
        }
    }

    public void setPosterResource(@DrawableRes int posterResource) {
        if (advertisement != null) {
            advertisement.setImageResource(posterResource);
        }
    }


    public void setPosterBitmap(Bitmap bitmap) {
        if (advertisement != null) {
            advertisement.setImageBitmap(bitmap);
        }
    }
}
