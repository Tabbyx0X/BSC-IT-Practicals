package com.example.practicals

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practicals.fragments.MyDialogFragment

/**
 * Practical 5 – Menus, Dialogs & DialogFragment Demo
 *
 * Demonstrates:
 * - Options menu (toolbar overflow menu) with one action item.
 * - AlertDialog shown on button press.
 * - Custom DialogFragment shown on button press.
 */
class Practical5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical5)

        // Set up toolbar as ActionBar
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar5)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Button: show a simple AlertDialog
        findViewById<Button>(R.id.btnShowAlertDialog).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("This is a simple AlertDialog.\nPress OK to dismiss.")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Button: show a DialogFragment
        findViewById<Button>(R.id.btnShowDialogFragment).setOnClickListener {
            MyDialogFragment().show(supportFragmentManager, "MyDialogFragment")
        }
    }

    // ── Options menu (toolbar menu) ──────────────────────────────────────

    /** Inflate the menu XML into the toolbar. */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_practical5, menu)
        return true
    }

    /** Handle the menu item tap. */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                Toast.makeText(this, "Info menu item tapped!", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
