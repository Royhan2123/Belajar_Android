package com.example.fundamentalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {
    private lateinit var btnCategory:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCategory = view.findViewById(R.id.btnCategory)

        btnCategory.setOnClickListener {
            val categoryFragment = CategoryFragment()
            val parentFragment = parentFragmentManager
            val bundle = Bundle()

            val descripsi = "Hello Everyone My name is Royhan and my old is twenty years old"
            bundle.putString(CategoryFragment.EXTRA_NAME,"BIODATA")

            categoryFragment.arguments = bundle
            categoryFragment.description = descripsi

            parentFragment.beginTransaction().apply {
                replace(R.id.container,categoryFragment,CategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}