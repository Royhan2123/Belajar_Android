package com.example.githubuserapp.ui.search

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.remote.Result
import com.example.githubuserapp.databinding.ActivitySearchBinding
import com.example.githubuserapp.ui.UserAdapter
import com.example.githubuserapp.ui.UserViewModel
import com.example.githubuserapp.ui.ViewModelFactory

class Search : AppCompatActivity() {
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: UserViewModel by viewModels { factory }

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val userAdapter = UserAdapter { user ->
            if (user.isFavorite) {
                viewModel.deleteUser(user)
            } else {
                viewModel.saveUser(user)
            }
        }

        // search
        val searchView = binding?.searchUser
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        binding?.progressBarSearch?.visibility = View.GONE

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null || query == "") {
                    return false
                }
                viewModel.getSearchUser(query).observe(this@Search) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                binding?.progressBarSearch?.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding?.progressBarSearch?.visibility = View.GONE
                                val userData = result.data
//                                    .filter {
//                                    it.userLogin.contains(query)
//                                }
                                userAdapter.submitList(userData)
                            }
                            is Result.Error -> {
                                binding?.progressBarSearch?.visibility = View.GONE
                                Toast.makeText(
                                    this@Search,
                                    "Terjadi kesalahan" + result.err,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


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

}