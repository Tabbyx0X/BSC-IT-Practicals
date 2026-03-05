package com.example.practicals

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Practical 4 – UI Elements Demo
 *
 * Demonstrates Material AppBar/Toolbar and common UI widgets:
 * - TextView
 * - Button (with click listener)
 * - EditText (with read-back button)
 * - CheckBox
 * - Switch (SwitchCompat)
 *
 * The Toolbar is set as the ActionBar via the XML layout's
 * MaterialToolbar; no extra Kotlin setup needed for the AppBar itself.
 */
@Suppress("DEPRECATION") // Switch widget – use SwitchMaterial for full Material 3 style
class Practical4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical4)

        // Set up the toolbar as the ActionBar so the back arrow works
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar4)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etInput = findViewById<EditText>(R.id.etInput)
        val cbCheck = findViewById<CheckBox>(R.id.cbCheck)
        val swToggle = findViewById<Switch>(R.id.swToggle)

        // Show a Toast when the button is clicked
        findViewById<Button>(R.id.btnClickMe).setOnClickListener {
            Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
        }

        // Read back the text entered in the EditText
        findViewById<Button>(R.id.btnReadInput).setOnClickListener {
            val text = etInput.text.toString().trim()
            val msg = if (text.isEmpty()) "EditText is empty" else "You typed: $text"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        // Show current state of CheckBox
        cbCheck.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "CheckBox: ${if (isChecked) "Checked" else "Unchecked"}", Toast.LENGTH_SHORT).show()
        }

        // Show current state of Switch
        swToggle.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Switch: ${if (isChecked) "ON" else "OFF"}", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the Up/Back arrow in the Toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
