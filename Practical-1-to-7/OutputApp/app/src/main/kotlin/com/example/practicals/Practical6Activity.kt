package com.example.practicals

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicals.adapter.ItemAdapter

/**
 * Practical 6 – Intents, Events, Listeners & Adapters Demo
 *
 * Demonstrates:
 * - Implicit Intent to open a website in the browser.
 * - RecyclerView with a custom ItemAdapter.
 * - Item-click listener showing a Toast with the selected item.
 */
class Practical6Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical6)

        // ── Implicit Intent ───────────────────────────────────────────────
        findViewById<Button>(R.id.btnOpenWebsite).setOnClickListener {
            // Open the Android developer website using an implicit intent
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            startActivity(intent)
        }

        // ── RecyclerView with Adapter ─────────────────────────────────────
        val items = listOf(
            "Item 1 – Apple",
            "Item 2 – Banana",
            "Item 3 – Cherry",
            "Item 4 – Date",
            "Item 5 – Elderberry",
            "Item 6 – Fig",
            "Item 7 – Grape"
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Pass an item-click listener to the adapter
        recyclerView.adapter = ItemAdapter(items) { clickedItem ->
            Toast.makeText(this, "Tapped: $clickedItem", Toast.LENGTH_SHORT).show()
        }
    }
}
