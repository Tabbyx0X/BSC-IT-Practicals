package com.example.practicals.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicals.R

/**
 * Fragment A – shown by default inside Practical2Activity.
 * Demonstrates a basic Fragment with its own layout.
 */
class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_a, container, false)
}
