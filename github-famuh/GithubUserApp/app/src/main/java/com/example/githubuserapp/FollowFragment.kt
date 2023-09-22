package com.example.githubuserapp

import android.os.Bundle
import android.text.style.TtsSpan.ARG_USERNAME
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.databinding.FragmentFollowBinding


class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        binding = FragmentFollowBinding.inflate(layoutInflater)

        // rv
        binding.recyclerView.setHasFixedSize(true)

        // list

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecoration)

        // observe
        detailViewModel.isLoadingFolls.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username = arguments?.getString(ARG_USERNAME)
        Log.d("USERNAME", "$username")

        if (index == 1) {
            detailViewModel.findUserFollowings(username!!)
            detailViewModel.userFollowing.observe(viewLifecycleOwner) { follow ->
                setUserFollowData(follow)
            }
        }

        if (index == 2){
            detailViewModel.findUserFollowings(username!!)
            detailViewModel.userFollowers.observe(viewLifecycleOwner) { follow ->
                setUserFollowData(follow)
            }
        }
    }

    private fun setUserFollowData(follow: List<FollowItem>) {
        val followAdapter = FollowAdapter(requireContext(), follow)
        binding.recyclerView.adapter = followAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFragment.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}