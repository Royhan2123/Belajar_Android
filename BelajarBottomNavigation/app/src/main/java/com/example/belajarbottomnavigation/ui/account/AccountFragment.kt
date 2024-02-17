package com.example.belajarbottomnavigation.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.belajarbottomnavigation.R
import com.example.belajarbottomnavigation.databinding.FragmentAccountBinding

class AccountFragment : Fragment() { 

    private var _binding : FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnActivity.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_account_to_account)
        }
    }
}