package com.example.lifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d(TAG, "SecondActivity: onCreate")

        val btnGoBack = findViewById<Button>(R.id.btnGoBack)
        btnGoBack.setOnClickListener {
            finish() // closes this activity and returns to LifecycleDemoActivity
        }
    }

    companion object {
        private const val TAG = "LifecycleDemo"
    }
}
