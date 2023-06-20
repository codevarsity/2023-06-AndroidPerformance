package com.example.appmemorytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MainActivityLeak extends AppCompatActivity {

    String name = "Hello World";
    static MyInnerClass innerObject = null;
    static Context context = null;
    class MyInnerClass {
        void printName() {
            Log.i("MainActivityLeak", name);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leak);

        if(innerObject == null) {
            innerObject = new MyInnerClass();
        }

        if(context != null) {
            context = this;
        }
    }
}