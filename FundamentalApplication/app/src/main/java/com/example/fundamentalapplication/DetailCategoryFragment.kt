package com.example.fundamentalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DetailCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnHomePage:Button = view.findViewById(R.id.btnHome)

        btnHomePage.setOnClickListener {
            val homeFragment = HomeFragment()
            val fragmentParent = parentFragmentManager

            fragmentParent.beginTransaction().apply {
                replace(R.id.container,homeFragment,HomeFragment::class.java.simpleName)
                commit()
            }
        }
    }
}