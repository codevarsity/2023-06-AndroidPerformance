package com.example.appmemorytest;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class ImageHandler extends Handler {
    WeakReference<ImageView> imageView;

    ImageHandler(Looper looper, ImageView imageView) {
        super(looper);
        this.imageView = new WeakReference<>(imageView);

    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Bitmap image = msg.getData().getParcelable("IMAGE");
        if(imageView.get() != null) {
            imageView.get().setImageBitmap(image);
        }
    }
}
