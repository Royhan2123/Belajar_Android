package com.example.githubuserapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

        if (index == 1) {
            detailViewModel.userFollowing.observe(viewLifecycleOwner) { follow ->
                setUserFollowData(follow)
            }
        } else {
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