package com.example.submissiondicoding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        checkField()
        appearAnimation()

        // to register
        binding.register.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }
    }

    private fun appearAnimation() {
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(700)
        val emailText =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(700)
        val emailEditlayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(700)
        val passwordText =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(700)
        val passwordEditLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(700)
        val btn = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(700)
        val register = ObjectAnimator.ofFloat(binding.register, View.ALPHA, 1f).setDuration(700)
        val orText = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(700)

        AnimatorSet().apply {
            playSequentially(
                title,
                emailText,
                emailEditlayout,
                passwordText,
                passwordEditLayout,
                btn,
                orText,
                register,
            )
        }.start()
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
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this@Login,
                                    "Login Success",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                val intent = Intent(this@Login, MainActivity::class.java)
                                startActivity(intent)
                            }

                            is Result.Error -> {
                                Toast.makeText(
                                    this@Login,
                                    "Login Failed",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }
}