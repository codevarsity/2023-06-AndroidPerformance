package com.example.appmemorytest

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    lateinit var timer:Timer
    lateinit var timer1:Timer

    var textArray = mutableListOf<String>()
    var bitmapArray = mutableListOf<Bitmap>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    


//        timer = Timer()
//        timer.scheduleAtFixedRate(object: TimerTask() {
//            override fun run() {
//                textArray.add(loadText())
//            }
//        }, Date(), 1000)
//
//        timer1 = Timer()
//        timer1.scheduleAtFixedRate(object: TimerTask() {
//            override fun run() {
//                bitmapArray.add(loadBitmap())
//            }
//        }, Date(), 1000)

    }

    fun loadBitmap(): Bitmap {
        val assets = getAssets()
        val fin = assets.open("ikigai.jpeg")
        val bitmap  = BitmapFactory.decodeStream(fin)
        return bitmap



    }


    fun loadText():String {
        val assets = getAssets()
        val fin = assets.open("test.txt")
        val inStr = InputStreamReader(fin)
        val bufferedStream = BufferedReader(inStr)
        var builder = StringBuilder()
        var line:String? = null
        line = bufferedStream.readLine()
        if(line != null) {
            builder.append(line)
        }

        while(line != null) {
            line = bufferedStream.readLine()
            if (line != null) {
                builder.append(line)
            }
        }

        return builder.toString()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        textArray.clear()
    }
}