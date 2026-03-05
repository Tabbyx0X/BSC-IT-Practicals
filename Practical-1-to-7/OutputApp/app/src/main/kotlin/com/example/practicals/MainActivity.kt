package com.example.practicals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

/**
 * MainActivity – the launcher / home screen.
 * Shows 7 buttons, one per practical. Tapping a button opens the
 * corresponding Activity that demonstrates the practical's output.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Map each button to its destination Activity
        val buttons = listOf(
            R.id.btnPractical1 to Practical1Activity::class.java,
            R.id.btnPractical2 to Practical2Activity::class.java,
            R.id.btnPractical3 to Practical3Activity::class.java,
            R.id.btnPractical4 to Practical4Activity::class.java,
            R.id.btnPractical5 to Practical5Activity::class.java,
            R.id.btnPractical6 to Practical6Activity::class.java,
            R.id.btnPractical7 to Practical7Activity::class.java
        )

        for ((id, activityClass) in buttons) {
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        }
    }
}
