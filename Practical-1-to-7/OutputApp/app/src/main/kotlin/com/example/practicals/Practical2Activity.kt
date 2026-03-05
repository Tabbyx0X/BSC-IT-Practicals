package com.example.practicals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.practicals.fragments.FragmentA
import com.example.practicals.fragments.FragmentB

/**
 * Practical 2 – Activities & Fragments Demo
 *
 * Demonstrates:
 * - Activity lifecycle callbacks logged on-screen.
 * - Navigation to a Second Activity.
 * - Switching between two Fragments inside this Activity.
 */
class Practical2Activity : AppCompatActivity() {

    private lateinit var tvLifecycleLog: TextView

    // Keep track of which fragment is shown
    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical2)

        tvLifecycleLog = findViewById(R.id.tvLifecycleLog)

        // Show Fragment A by default when the activity is first created
        if (savedInstanceState == null) {
            loadFragment(FragmentA())
        }

        // Button: switch between Fragment A and Fragment B
        findViewById<Button>(R.id.btnSwitchFragment).setOnClickListener {
            if (showingFragmentA) {
                loadFragment(FragmentB())
                showingFragmentA = false
            } else {
                loadFragment(FragmentA())
                showingFragmentA = true
            }
        }

        // Button: open Second Activity
        findViewById<Button>(R.id.btnOpenSecondActivity).setOnClickListener {
            startActivity(Intent(this, Practical2SecondActivity::class.java))
        }

        appendLog("onCreate() called")
    }

    /** Replace the fragment container with the given fragment. */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // ── Lifecycle callbacks – each appends a line to the on-screen log ──

    override fun onStart() {
        super.onStart()
        appendLog("onStart() called")
    }

    override fun onResume() {
        super.onResume()
        appendLog("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        appendLog("onPause() called")
    }

    override fun onStop() {
        super.onStop()
        appendLog("onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        appendLog("onDestroy() called")
    }

    /** Appends a lifecycle event line to the on-screen log TextView. */
    private fun appendLog(msg: String) {
        val current = tvLifecycleLog.text.toString()
        tvLifecycleLog.text = if (current.isEmpty()) msg else "$current\n$msg"
    }
}
