package com.example.fundamentalapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CategoryFragment : Fragment() {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    private lateinit var txtName: TextView
    private lateinit var txtDescription: TextView

    var description: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtName = view.findViewById(R.id.txtName)
        txtDescription = view.findViewById(R.id.txtDescription)

        if (savedInstanceState != null){
            val bundles = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = bundles
        }

        if (arguments != null){
            val argument = arguments?.getString(EXTRA_NAME)
            txtName.text = argument
            txtDescription.text = description
        }
    }
}