package com.example.opencvtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NDKTestActivity : AppCompatActivity() {

    init {
        System.loadLibrary("MyLibs")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ndktest)

        findViewById<TextView>(R.id.textView).text = NativeClass.getMessageFromJNI()
    }
}
