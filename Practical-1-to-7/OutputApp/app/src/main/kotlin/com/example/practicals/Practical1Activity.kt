package com.example.practicals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Practical 1 – Resources Demo
 *
 * Demonstrates:
 * - String resource  (@string/p1_greeting)
 * - Color resource   (background color applied via XML)
 * - Drawable resource (vector image shown in an ImageView)
 * - Dimension resource (padding applied via @dimen/p1_padding)
 *
 * All demonstration is done entirely in the XML layout –
 * no special Kotlin code is needed beyond setting the content view.
 */
class Practical1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical1)
    }
}
