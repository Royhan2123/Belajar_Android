package com.example.fundamentaandroidnavigations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fundamentaandroidnavigations.databinding.FragmentDetailBinding
import com.example.fundamentaandroidnavigations.databinding.FragmentHomeBinding

class Detail : Fragment() {
    private var bindings:FragmentDetailBinding? = null
    private  val binding get() = bindings!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        bindings = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataName = arguments?.getString(Category.name)
        val dataUmur = arguments?.getString(Category.umur)
        val dataAlamat = arguments?.getString(Category.alamat)

        binding.txtNama.text = "Nama Saya adalah $dataName"
        binding.txtUmur.text = "umur Saya adalah $dataUmur"
        binding.txtAlamat.text = "Alamat Saya adalah $dataAlamat"

        binding.btnDetail.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detail_to_home)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        bindings = null
    }
}