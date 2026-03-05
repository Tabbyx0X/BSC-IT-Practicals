package com.example.practicals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Practical 3 – Layouts Demo
 *
 * Demonstrates three common Android layout types:
 * - LinearLayout  (vertical stack of views)
 * - RelativeLayout (views positioned relative to each other)
 * - ConstraintLayout (views constrained to parent / siblings)
 *
 * All three sections are shown in a single scrollable layout.
 * No special Kotlin code is required – the layout XML does the work.
 */
class Practical3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical3)
    }
}
