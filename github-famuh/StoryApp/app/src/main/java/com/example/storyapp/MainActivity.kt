package com.example.storyapp

import com.example.storyapp.auth.AuthViewModelFactory
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.UI.addStory.AddStoryActivity
import com.example.storyapp.databinding.ActivityMainBinding
import com.example.storyapp.UI.main.MainViewModel
import com.example.storyapp.UI.main.UserStoryAdapter
import com.example.storyapp.api.Result
import com.example.storyapp.auth.login.LoginActivity
import com.example.storyapp.auth.login.LoginViewModel
import com.example.storyapp.di.Injection
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var storyViewModel: StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        loginState()
        setupAction()
        setupEvent()
    }

    override fun onResume() {
        super.onResume()
        setupEvent()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun loginState() {
        loginViewModel.readLoginState().observe(this) { isLogin ->
            if (isLogin) {
                supportActionBar?.title = "Hello, "
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
            Log.d("MAIN", "isLogin : $isLogin")
        }
    }

    private fun setupViewModel() {
        // Read Login State
        val userPref = UserPreference.getInstance(dataStore)
        val repository = Injection.provideRepository(this)
        val viewModelFactory = AuthViewModelFactory(repository, userPref)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        storyViewModel = ViewModelProvider(this, viewModelFactory)[StoryViewModel::class.java]
    }


    private fun setupAction() {
        // action logout
        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.logout()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

        // action add story
        binding.addStory.setOnClickListener {
            val intent = Intent(this, AddStoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupEvent() {
        // Adapter Config
        val userStoryAdapter = UserStoryAdapter()
        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvStory.layoutManager = layoutManager
        binding.rvStory.adapter = userStoryAdapter

        // Fetch data
        loginViewModel.readToken().observe(this) { token ->
            Log.d("Token Di Main", token)
            storyViewModel.getHeadlineStory(token).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val storyData = result.data
                            userStoryAdapter.submitList(storyData)

                            layoutManager.scrollToPosition(0)
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@MainActivity,
                                "An Error Occuired : " + result.err,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
