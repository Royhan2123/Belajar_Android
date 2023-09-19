package com.example.fundamentalandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnHomeFragment : Button = view.findViewById(R.id.btn_home)

        btnHomeFragment.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_home){
            val fragmentManager = parentFragmentManager
            val category = CategoryFragment()
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container,category,CategoryFragment::class.java.simpleName)
                .addToBackStack(null).commit()
            }
        }
    }
}