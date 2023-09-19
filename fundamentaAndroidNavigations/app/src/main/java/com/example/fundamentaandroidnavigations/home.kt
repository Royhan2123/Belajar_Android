package com.example.fundamentaandroidnavigations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.fundamentaandroidnavigations.databinding.FragmentHomeBinding

class home : Fragment() {
    private var bindings:FragmentHomeBinding? = null
    private  val binding get() = bindings!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHome.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_home_to_category
            )
        )
        binding.btnProfil.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_home_to_profile)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindings = null
    }
}