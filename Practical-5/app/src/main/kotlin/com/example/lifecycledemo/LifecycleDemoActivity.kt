package com.example.lifecycledemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LifecycleDemoActivity : AppCompatActivity() {

    private lateinit var tvCurrentEvent: TextView
    private lateinit var tvLog: TextView
    private lateinit var scrollView: ScrollView
    private lateinit var etSaveDemo: EditText

    private val logEntries = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_demo)

        tvCurrentEvent = findViewById(R.id.tvCurrentEvent)
        tvLog = findViewById(R.id.tvLog)
        scrollView = findViewById(R.id.scrollView)
        etSaveDemo = findViewById(R.id.etSaveDemo)

        val btnOpenSecond = findViewById<Button>(R.id.btnOpenSecond)
        btnOpenSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // Restore saved text if activity was recreated (e.g. on rotation)
        savedInstanceState?.let {
            val savedText = it.getString(KEY_EDIT_TEXT, "")
            etSaveDemo.setText(savedText)
            addLog("onCreate [RESTORED] — text field state was restored: \"$savedText\"")
        } ?: addLog("onCreate — activity created fresh")
    }

    override fun onStart() {
        super.onStart()
        addLog("onStart — activity is now visible")
    }

    override fun onResume() {
        super.onResume()
        addLog("onResume — activity is in the foreground and interactive")
    }

    override fun onPause() {
        super.onPause()
        addLog("onPause — activity is partially hidden (another activity on top)")
    }

    override fun onStop() {
        super.onStop()
        addLog("onStop — activity is no longer visible")
    }

    override fun onRestart() {
        super.onRestart()
        addLog("onRestart — activity is coming back from stopped state")
    }

    override fun onDestroy() {
        super.onDestroy()
        addLog("onDestroy — activity is being destroyed")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the EditText content so it survives rotation / system kill
        outState.putString(KEY_EDIT_TEXT, etSaveDemo.text.toString())
        addLog("onSaveInstanceState — saving text: \"${etSaveDemo.text}\"")
    }

    private fun addLog(message: String) {
        Log.d(TAG, message)
        logEntries.append("• $message\n")
        tvCurrentEvent.text = "Latest: $message"
        tvLog.text = logEntries.toString()
        // Auto-scroll to the bottom so newest entry is always visible
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    companion object {
        private const val TAG = "LifecycleDemo"
        private const val KEY_EDIT_TEXT = "key_edit_text"
    }
}
