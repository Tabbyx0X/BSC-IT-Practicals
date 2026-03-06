package com.example.airplanemodenotifier

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Practical 8 – Airplane Mode Notifier
 *
 * Displays the current Airplane Mode state and updates it live via a local
 * broadcast forwarded by [AirplaneModeReceiver].  Also requests the
 * POST_NOTIFICATIONS runtime permission on Android 13+.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView

    // ── Permission launcher for POST_NOTIFICATIONS (Android 13+) ─────────
    private val notifPermLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (!granted) {
                Toast.makeText(
                    this,
                    "Notification permission denied – notifications won't appear.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    // ── Local receiver – listens for updates from AirplaneModeReceiver ────
    private val statusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == AirplaneModeReceiver.ACTION_AIRPLANE_STATUS_CHANGED) {
                val isEnabled = intent.getBooleanExtra(AirplaneModeReceiver.EXTRA_IS_ENABLED, false)
                updateStatusText(isEnabled)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)

        // Show current state immediately when the app opens
        updateStatusText(isAirplaneModeOn())

        // Ensure notification channel exists (belt-and-suspenders; receiver also creates it)
        createNotificationChannel()

        // Request POST_NOTIFICATIONS permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notifPermLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Register for local updates forwarded by AirplaneModeReceiver
        val filter = IntentFilter(AirplaneModeReceiver.ACTION_AIRPLANE_STATUS_CHANGED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(statusReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            registerReceiver(statusReceiver, filter)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(statusReceiver)
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    private fun isAirplaneModeOn(): Boolean =
        Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1

    private fun updateStatusText(isEnabled: Boolean) {
        tvStatus.text = if (isEnabled) {
            getString(R.string.status_on)
        } else {
            getString(R.string.status_off)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AirplaneModeReceiver.CHANNEL_ID,
                "Airplane Mode Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifies when Airplane Mode is turned ON or OFF"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
