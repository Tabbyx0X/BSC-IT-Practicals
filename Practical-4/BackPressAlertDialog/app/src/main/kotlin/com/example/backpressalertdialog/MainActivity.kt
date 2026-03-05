package com.example.backpressalertdialog

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Register a callback on the modern back-press dispatcher.
        // This replaces the deprecated onBackPressed() override.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })
    }

    /**
     * Shows an AlertDialog asking the user whether they want to exit the app.
     * "Yes" finishes the activity; "No" dismisses the dialog and stays in the app.
     */
    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            // "Yes" button – close the activity (and the app if it is the only one)
            .setPositiveButton(getString(R.string.btn_yes)) { _, _ -> finish() }
            // "No" button – simply dismiss the dialog and stay on screen
            .setNegativeButton(getString(R.string.btn_no)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false) // prevent accidental dismiss by tapping outside
            .show()
    }
}
