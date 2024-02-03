package com.example.belajarbottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.belajarbottomnavigation.databinding.FragmentTabSearchBinding

class FragmentTabSearch : Fragment() {
    private var _binding : FragmentTabSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabSearchBinding.inflate(inflater,container,false)
        return binding.root
    }
}