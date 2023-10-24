package com.example.submissiondicoding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.submissiondicoding.databinding.ActivityLoginBinding
import com.example.submissiondicoding.di.Injection
import com.example.submissiondicoding.model.LoginViewModel
import com.example.submissiondicoding.model.ViewModelFactory
import com.example.submissiondicoding.preferences.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        checkField()
        playAnimation()
        // untuk mendaftar
        binding.register.setOnClickListener {
            val intent = Intent(this@LoginActivity, Register::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Recycle")
    private fun playAnimation(){
        // Set visibility
        binding.imgLogo.visibility = View.VISIBLE
        binding.txtloginAccount.visibility = View.VISIBLE
        binding.tvEmailTitle.visibility = View.VISIBLE
        binding.edtEmail.visibility = View.VISIBLE
        binding.tvPasswordTitle.visibility = View.VISIBLE
        binding.edtPassword.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.VISIBLE
        binding.layoutTextRegister.visibility = View.VISIBLE
        binding.tvIsHaventAccount.visibility = View.VISIBLE
        binding.tvToRegister.visibility = View.VISIBLE


        binding.imgLogo.alpha = 0f
        binding.txtloginAccount.alpha = 0f
        binding.tvEmailTitle.alpha = 0f
        binding.edtEmail.alpha = 0f
        binding.tvPasswordTitle.alpha = 0f
        binding.edtPassword.alpha = 0f
        binding.btnLogin.alpha = 0f
        binding.layoutTextRegister.alpha = 0f
        binding.tvIsHaventAccount.alpha = 0f
        binding.tvToRegister.alpha = 0f

        // Create animator set
        val animatorSet = AnimatorSet()
        ObjectAnimator.ofFloat(binding.imgLogo, View.TRANSLATION_X,-50f,50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        animatorSet.playSequentially(
            ObjectAnimator.ofFloat(binding.imgLogo, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.txtloginAccount, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvEmailTitle, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvPasswordTitle, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.layoutTextRegister, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvIsHaventAccount, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvToRegister, View.ALPHA, 1f).setDuration(500),
        )
        // Start animation
        animatorSet.start()
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

    private fun checkField() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Masukkan password"
                }
                else -> {
                    val repository = Injection.provideRepository(this)
                    val userPreference = UserPreference.getInstance(this.dataStore)
                    val factory = ViewModelFactory(repository, userPreference)

                    val loginViewModel: LoginViewModel by viewModels { factory }

                    loginViewModel.loginAccount(email, password)
                    loginViewModel.loginResult.observe(this) { result ->
                        when (result) {
                            is com.example.submissiondicoding.api.Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is com.example.submissiondicoding.api.Result.Success -> {
                                binding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                            is com.example.submissiondicoding.api.Result.Error -> {
                                Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}
