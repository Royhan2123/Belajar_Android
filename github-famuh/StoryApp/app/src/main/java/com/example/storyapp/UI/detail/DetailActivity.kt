package com.example.storyapp.UI.detail

import android.content.Context
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
import com.bumptech.glide.Glide
import com.example.storyapp.R
import com.example.storyapp.StoryViewModel
import com.example.storyapp.api.Result
import com.example.storyapp.auth.AuthViewModelFactory
import com.example.storyapp.auth.login.LoginViewModel
import com.example.storyapp.databinding.ActivityDetailBinding
import com.example.storyapp.di.Injection
import com.example.storyapp.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var storyViewModel: StoryViewModel

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // id from main
        val id = intent.getStringExtra(EXTRA_ID)

        setupView()
        setupViewModel()

        if (id != null && id.isNotEmpty()) {
            setupEvent(id)
        }else{
            Toast.makeText(this@DetailActivity, "An Error Occuired", Toast.LENGTH_SHORT).show()
        }

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

    private fun setupViewModel() {
        val userPref = UserPreference.getInstance(dataStore)
        val repository = Injection.provideRepository(this)
        val viewModelFactory = AuthViewModelFactory(repository, userPref)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        storyViewModel = ViewModelProvider(this, viewModelFactory)[StoryViewModel::class.java]
    }

    private fun setupEvent(id: String) {
        // Read token
        loginViewModel.readToken().observe(this) { token ->
            // getDetail Event
            storyViewModel.getStoryDetail(token, id).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val storyData = result.data

                            // fetch
                            Glide.with(this)
                                .load(storyData.photoUrl)
                                .error(R.drawable.ic_baseline_broken_image_24)
                                .centerCrop()
                                .into(binding.ivDetailPhoto)
                            binding.tvDetailName.text = storyData.name
                            binding.tvDetailDescription.text = storyData.description
                            Log.d("Main", "user : ${result.data.name}")
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@DetailActivity,
                                "An Error Occuired" + result.err,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}