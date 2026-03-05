package com.example.airplanemodenotifier

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat

/**
 * Manifest-declared BroadcastReceiver that fires whenever Airplane Mode is toggled.
 *
 * Reads the new state from the intent extra "state" (boolean) and also cross-checks
 * the system setting for reliability.  Posts a notification and a Toast.
 */
class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_AIRPLANE_MODE_CHANGED) return

        // Read state from the system setting (most reliable source)
        val isEnabled = Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) == 1

        val statusText = if (isEnabled) "Airplane Mode: ON ✈" else "Airplane Mode: OFF 📶"

        // Show Toast for immediate feedback
        Toast.makeText(context, statusText, Toast.LENGTH_SHORT).show()

        // Post a notification
        showNotification(context, isEnabled, statusText)

        // Notify the running Activity (if any) via a local broadcast
        val localIntent = Intent(ACTION_AIRPLANE_STATUS_CHANGED).apply {
            putExtra(EXTRA_IS_ENABLED, isEnabled)
            setPackage(context.packageName)
        }
        context.sendBroadcast(localIntent)
    }

    // ── Notification helpers ──────────────────────────────────────────────

    private fun showNotification(context: Context, isEnabled: Boolean, statusText: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(manager)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Airplane Mode Changed")
            .setContentText(statusText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        manager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(manager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Airplane Mode Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifies when Airplane Mode is turned ON or OFF"
            }
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "airplane_mode_channel"
        const val NOTIFICATION_ID = 8
        const val ACTION_AIRPLANE_STATUS_CHANGED =
            "com.example.airplanemodenotifier.AIRPLANE_STATUS_CHANGED"
        const val EXTRA_IS_ENABLED = "is_enabled"
    }
}
