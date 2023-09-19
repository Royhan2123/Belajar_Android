package com.example.fundamentalandroidnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fundamentalandroidnavigation.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private var _binding:FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    companion object{
        var name = "name"
        var umur = "umur"
        var tinggiBadan = "Tinggibadan"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater,container,false)
        val view = binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKirimData.setOnClickListener {
            view -> val bundle = Bundle()
            bundle.putString(name,"Royhan")
            bundle.putInt(umur,19)
            bundle.putDouble(tinggiBadan,168.0)
            view.findNavController().navigate(R.id.action_categoryFragment_to_detail,bundle)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}