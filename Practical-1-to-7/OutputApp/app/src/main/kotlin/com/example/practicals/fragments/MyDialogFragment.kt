package com.example.practicals.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * MyDialogFragment – a custom DialogFragment shown from Practical5Activity.
 * Demonstrates creating a dialog inside a Fragment (recommended approach).
 */
class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("DialogFragment")
            .setMessage("This dialog was created using a DialogFragment.\nIt survives rotation automatically!")
            .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}
