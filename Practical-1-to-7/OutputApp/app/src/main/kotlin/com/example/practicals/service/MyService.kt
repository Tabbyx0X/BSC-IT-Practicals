package com.example.practicals.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * MyService – a simple background Service for Practical 7.
 *
 * In a real app this would perform background work (e.g. downloading data).
 * Here it just logs start/stop messages to demonstrate the Service lifecycle.
 */
class MyService : Service() {

    companion object {
        private const val TAG = "MyService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand() – service is running")
        // START_NOT_STICKY means the system will NOT recreate this service
        // automatically if it is killed while there are no pending intents.
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service onDestroy() – service stopped")
    }

    /** This is not a bound service, so we return null. */
    override fun onBind(intent: Intent?): IBinder? = null
}
