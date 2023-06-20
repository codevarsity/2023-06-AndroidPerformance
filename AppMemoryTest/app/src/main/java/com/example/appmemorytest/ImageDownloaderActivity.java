package com.example.appmemorytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloaderActivity extends AppCompatActivity {

    Button downloadButton;
    ImageView imageView;

    ImageHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);

        downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Bitmap image = getImage("https://trendblog.net/wp-content/uploads/2013/06/white-android-logo_00039624.jpg");

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("IMAGE", image);
                        Message msg = handler.obtainMessage();
                        msg.setData(bundle);
                        handler.sendMessageDelayed(msg, 1000000);
                    }
                };
                Thread th = new Thread(runnable);
                th.start();
            }
        });

        imageView = findViewById(R.id.imageView);
        handler = new ImageHandler(Looper.getMainLooper(), imageView);

    }

    Bitmap getImage(String link) {
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream str = connection.getInputStream();
            Bitmap result = BitmapFactory.decodeStream(str);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}