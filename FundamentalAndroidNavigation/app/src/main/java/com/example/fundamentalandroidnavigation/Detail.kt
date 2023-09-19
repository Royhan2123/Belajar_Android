package com.example.fundamentalandroidnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fundamentalandroidnavigation.databinding.FragmentDetailBinding

class Detail : Fragment() {
    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataName = arguments?.getString(CategoryFragment.name)
        val dataUmur = arguments?.getInt(CategoryFragment.umur)
        val dataTinggiBadan = arguments?.getDouble(CategoryFragment.tinggiBadan)

        binding.txtName.text = "Nama saya adalah $dataName"
        binding.txtUmur.text = "Umur saya adalah $dataUmur"
        binding.txtTinggiBadan.text = "Tinggi Badan  saya adalah $dataTinggiBadan"

        binding.btnHome.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detail_to_homeFragment)
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}