package com.example.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.LruCache;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String imageLink = "https://www.freepnglogos.com/uploads/android-logo-png/android-logo-png-transparent-images-and-icons-9.png";

    ImageView imageView;

    ExecutorService threadService = Executors.newFixedThreadPool(4);

    LruCache<String, Bitmap> imageCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

//        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        manager.getMemoryClass();
        imageCache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };



    }


    private Bitmap getImage(String link) {
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inStr = connection.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(inStr);
            return image;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadImage(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(null);
                    }
                });
                Bitmap image = getMemoryCachedImage(imageLink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(image);
                    }
                });
            }
        };

      threadService.execute(runnable);
    }

    private boolean fileExists(String name) {
        for ( String fsFile : fileList() ) {
            if( fsFile.equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    private Bitmap getMemoryCachedImage(String url) {
        String fileName = URLUtil.guessFileName(url, null, null);
        Bitmap image = imageCache.get(fileName);
        if(image != null) {
            return image;
        } else {
            image = getFromDiskCache(url);
            imageCache.put(fileName, image);
            return image;
        }
    }
    private Bitmap getFromDiskCache(String url) {
        String fileName = URLUtil.guessFileName(url, null, null);
        Bitmap image = null;
        if(fileExists(fileName)) {
            try {
                FileInputStream inStr = openFileInput(fileName);
                image = BitmapFactory.decodeStream(inStr);
                return image;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            image = getImage(url);
            try {
                FileOutputStream outStr = openFileOutput(fileName, MODE_PRIVATE);
                image.compress(Bitmap.CompressFormat.PNG, 90, outStr);
                return image;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}