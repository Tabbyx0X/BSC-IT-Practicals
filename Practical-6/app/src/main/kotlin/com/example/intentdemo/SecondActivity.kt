package com.example.intentdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME) ?: ""
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE) ?: ""

        val tvName: TextView = findViewById(R.id.tvNameValue)
        val tvMessage: TextView = findViewById(R.id.tvMessageValue)

        tvName.text = name
        tvMessage.text = message

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
