package com.example.githubuserapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.FragmentFollowBinding
import com.example.githubuserapp.ui.UserAdapter


class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var username: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFollowBinding.inflate(layoutInflater)

        // rv
        binding.recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecoration)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(DetailUser.EXTRA_USERNAME).toString()

        Log.d("Fol Frag", "username follower : $username ")

        detailViewModel.isLoadingFolls.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        detailViewModel.findUserFollowers(username)
        detailViewModel.userFollowers.observe(viewLifecycleOwner) { follow ->
            val adapter = FollowAdapter(requireContext(), follow)
            binding.recyclerView.adapter = adapter
        }
        Log.d("Fol Frag", "yang tampil follower ")
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFragment.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}