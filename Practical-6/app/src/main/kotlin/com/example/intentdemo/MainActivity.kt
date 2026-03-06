package com.example.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            val name = etName.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = getString(R.string.error_name_empty)
                etName.requestFocus()
                return@setOnClickListener
            }

            if (message.isEmpty()) {
                etMessage.error = getString(R.string.error_message_empty)
                etMessage.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_NAME = "com.example.intentdemo.EXTRA_NAME"
        const val EXTRA_MESSAGE = "com.example.intentdemo.EXTRA_MESSAGE"
    }
}
