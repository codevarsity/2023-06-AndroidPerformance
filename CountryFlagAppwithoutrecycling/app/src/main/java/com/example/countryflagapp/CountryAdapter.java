package com.example.countryflagapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ViewHolder {
    ImageView imageView;


}

public class CountryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Country> countries;
    ExecutorService service = Executors.newFixedThreadPool(5);

    ImageFetcher imageFetcher;

    Handler handler = new ImageHandler();

    public CountryAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;

        imageFetcher = new ImageFetcher(context);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View mainView = null;
        
            mainView = LayoutInflater.from(context).inflate(R.layout.row, null);
            ImageView imageView = mainView.findViewById(R.id.imageView);

        ImageView imageView = mainView.findViewById(R.id.imageView);

        //fetch image from server
        Country country = countries.get(i);
        String basePath = "https://www.worldometers.info/img/flags/";
        String countryIsoCode = country.isoCode;
        String flagPath = basePath + countryIsoCode + "-flag.gif";

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Bitmap image  = imageFetcher.getMemoryCachedImage(flagPath);
                Bitmap image = imageFetcher.getImage(flagPath);
                Runnable uiRunnable = new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(image);
                    }
                };
                handler.post(uiRunnable);

            }
        };

        service.execute(runnable);



        return mainView;
    }
}
