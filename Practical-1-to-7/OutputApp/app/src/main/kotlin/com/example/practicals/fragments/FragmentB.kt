package com.example.practicals.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicals.R

/**
 * Fragment B – shown when the user taps "Switch Fragment" inside Practical2Activity.
 * Demonstrates swapping Fragments at runtime.
 */
class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_b, container, false)
}
