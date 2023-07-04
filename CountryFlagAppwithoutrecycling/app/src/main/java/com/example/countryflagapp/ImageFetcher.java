package com.example.countryflagapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.ULocale;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.util.LruCache;
import android.webkit.URLUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageFetcher {
    Context context;

    LruCache<String, Bitmap> imageCache;

    public ImageFetcher(Context context) {
        this.context = context;

        int cacheSize = 4 * 1024 * 1024;
        imageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };


    }

    public Bitmap getImage(String link) {
        String fileName = URLUtil.guessFileName(link, null, null);
        if(exists(fileName)) {
            return getCachedImage(fileName);
        } else {
            Bitmap file = getNetworkImage(link);
            try {
                FileOutputStream fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                file.compress(Bitmap.CompressFormat.JPEG, 90, fout);
                fout.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return file;
        }
    }

    public Bitmap getMemoryCachedImage(String link) {
        String fileName = URLUtil.guessFileName(link, null, null);
        Bitmap image = imageCache.get(fileName);
        if(image != null) {
            return image;
        } else {
            image = getCachedImage(fileName);
            if (image != null) {
                imageCache.put(fileName, image);
                return image;
            } else {
                image = getNetworkImage(link);
                imageCache.put(fileName,image);
                try {
                    FileOutputStream fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                    image.compress(Bitmap.CompressFormat.JPEG, 90, fout);
                    fout.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return image;
    }

    private Bitmap getNetworkImage(String link) {
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inStr = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inStr);
            return bitmap;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Bitmap getCachedImage(String fileName) {
        try {
            FileInputStream fin = context.openFileInput(fileName);
            Bitmap image = BitmapFactory.decodeStream(fin);
            return image;
        } catch (FileNotFoundException e) {
            return null;
        }

    }
    private boolean exists(String fileName) {

        Log.i("ImageFetcher", fileName);
        String[] files = context.fileList();
        for(String file : files) {
            if(file.equalsIgnoreCase(fileName)) {
                return true;
            }
        }
        return false;
    }
 }
