package com.example.practicals

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.practicals.service.MyService

/**
 * Practical 7 – Services, Notifications & Broadcast Receivers Demo
 *
 * Demonstrates:
 * - A local BroadcastReceiver that listens for a custom action.
 * - Sending a notification (requests POST_NOTIFICATIONS permission on API 33+).
 * - Starting and stopping a simple background service (MyService).
 */
class Practical7Activity : AppCompatActivity() {

    companion object {
        /** Custom action used by the local broadcast. */
        const val ACTION_CUSTOM_BROADCAST = "com.example.practicals.CUSTOM_BROADCAST"
        /** Notification channel ID. */
        private const val CHANNEL_ID = "practicals_channel"
    }

    private lateinit var tvStatus: TextView

    // ── Permission launcher for POST_NOTIFICATIONS (Android 13+) ─────────
    private val notifPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                sendNotification()
            } else {
                Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_SHORT).show()
            }
        }

    // ── Local BroadcastReceiver ───────────────────────────────────────────
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_CUSTOM_BROADCAST) {
                tvStatus.append("\n✔ Broadcast received! Action = $ACTION_CUSTOM_BROADCAST")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical7)

        tvStatus = findViewById(R.id.tvStatus7)

        // Create notification channel (required on API 26+)
        createNotificationChannel()

        // ── Broadcast button ──────────────────────────────────────────────
        findViewById<Button>(R.id.btnSendBroadcast).setOnClickListener {
            // Send a local broadcast with the custom action
            val intent = Intent(ACTION_CUSTOM_BROADCAST)
            intent.setPackage(packageName) // restrict to this app
            sendBroadcast(intent)
            tvStatus.append("\n→ Broadcast sent.")
        }

        // ── Notification button ───────────────────────────────────────────
        findViewById<Button>(R.id.btnSendNotification).setOnClickListener {
            // On Android 13+ we must ask the user for permission first
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permStatus = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
                if (permStatus == PackageManager.PERMISSION_GRANTED) {
                    sendNotification()
                } else {
                    notifPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                sendNotification()
            }
        }

        // ── Service buttons ───────────────────────────────────────────────
        findViewById<Button>(R.id.btnStartService).setOnClickListener {
            startService(Intent(this, MyService::class.java))
            tvStatus.append("\n→ Service started.")
        }

        findViewById<Button>(R.id.btnStopService).setOnClickListener {
            stopService(Intent(this, MyService::class.java))
            tvStatus.append("\n→ Service stopped.")
        }
    }

    override fun onStart() {
        super.onStart()
        // Register the broadcast receiver dynamically
        val filter = IntentFilter(ACTION_CUSTOM_BROADCAST)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            registerReceiver(broadcastReceiver, filter)
        }
    }

    override fun onStop() {
        super.onStop()
        // Unregister to avoid memory leaks
        unregisterReceiver(broadcastReceiver)
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    /** Creates the notification channel required on API 26+. */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Practicals Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Channel for Practical 7 demo notification" }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    /** Builds and shows a simple notification. */
    private fun sendNotification() {
        val manager = getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Practical 7 – Notification")
            .setContentText("This is a demo notification from the Practicals app!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        manager.notify(7, notification)
        tvStatus.append("\n✔ Notification sent.")
    }
}
