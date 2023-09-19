package com.example.fundamentaandroidnavigations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fundamentaandroidnavigations.databinding.FragmentCategoryBinding
import com.example.fundamentaandroidnavigations.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Category : Fragment() {
    private var bindings:FragmentCategoryBinding? = null
    private  val binding get() = bindings!!

    companion object{
        var name = "name"
        var umur = "umur"
        var alamat = "alamat"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentCategoryBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCategory.setOnClickListener {
            view -> val bundle = Bundle()
            bundle.putString(name,"Royhan")
            bundle.putInt(umur,19)
            bundle.putString(alamat,"Jln.Pelajar Timur Gg Kelapa Lorong Gabe")
            view.findNavController().navigate(R.id.action_category_to_detail,bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindings = null
    }
}