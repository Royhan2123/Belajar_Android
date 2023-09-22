package com.example.githubuserapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.FragmentUserBinding
import com.example.githubuserapp.data.remote.*


class UserFragment : Fragment() {

    private var tabName: String? = null

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)

        // inisiasi view model & menampilkan data sesuai dgn status yg didapat
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireContext())
        val viewModel: UserViewModel by viewModels { factory }

        val userAdapter = UserAdapter { user ->
            if (user.isFavorite) {
                viewModel.deleteUser(user)
            } else {
                viewModel.saveUser(user)
            }
        }


        if (tabName == TAB_USER) {
            viewModel.getHeadlineUser().observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            val userData = result.data
                            userAdapter.submitList(userData)
                        }
                        is Result.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "Terjadi kesalahan" + result.err,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        } else if (tabName == TAB_FAVORITE) {
            viewModel.getFavoriteUser().observe(viewLifecycleOwner) { favUser ->
                binding?.progressBar?.visibility = View.GONE
                userAdapter.submitList(favUser)
            }

        }

        binding?.rvUser?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_USER = "user"
        const val TAB_FAVORITE = "favorite"
    }
}