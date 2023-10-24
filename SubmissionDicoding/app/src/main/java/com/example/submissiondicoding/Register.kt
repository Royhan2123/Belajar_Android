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
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.submissiondicoding.databinding.ActivityRegisterBinding
import com.example.submissiondicoding.di.Injection
import com.example.submissiondicoding.model.SignupViewModel
import com.example.submissiondicoding.model.ViewModelFactory
import com.example.submissiondicoding.preferences.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }

    @SuppressLint("Recycle")
    private fun playAnimation(){
        // Set visibility
        binding.imgLogo.visibility = View.VISIBLE
        binding.txtCreateAccount.visibility = View.VISIBLE
        binding.tvNameTitle.visibility = View.VISIBLE
        binding.edtName.visibility = View.VISIBLE
        binding.tvEmailTitle.visibility = View.VISIBLE
        binding.edtEmail.visibility = View.VISIBLE
        binding.tvPasswordTitle.visibility = View.VISIBLE
        binding.edtPassword.visibility = View.VISIBLE
        binding.btnRegister.visibility = View.VISIBLE
        binding.layoutTextRegister.visibility = View.VISIBLE
        binding.tvIsHaveAccount.visibility = View.VISIBLE
        binding.tvToLogin.visibility = View.VISIBLE


        binding.imgLogo.alpha = 0f
        binding.tvNameTitle.alpha = 0f
        binding.txtCreateAccount.alpha = 0f
        binding.edtName.alpha = 0f
        binding.tvEmailTitle.alpha = 0f
        binding.edtEmail.alpha = 0f
        binding.tvPasswordTitle.alpha = 0f
        binding.edtPassword.alpha = 0f
        binding.btnRegister.alpha = 0f
        binding.layoutTextRegister.alpha = 0f
        binding.tvIsHaveAccount.alpha = 0f
        binding.tvToLogin.alpha = 0f

        // Create animator set
        val animatorSet = AnimatorSet()
        ObjectAnimator.ofFloat(binding.imgLogo, View.TRANSLATION_X,-50f,50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        animatorSet.playSequentially(
            ObjectAnimator.ofFloat(binding.imgLogo, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.txtCreateAccount, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvNameTitle, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.edtName, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvEmailTitle, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvPasswordTitle, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.layoutTextRegister, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvIsHaveAccount, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.tvToLogin, View.ALPHA, 1f).setDuration(500),
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

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameEditTextLayout.error = "Please input your name"
                }

                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Please input your email"
                }

                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Please input your password"
                }

                else -> {
                    val repository = Injection.provideRepository(this)
                    val userPreference = UserPreference.getInstance(this.dataStore)
                    val factory = ViewModelFactory(repository, userPreference)
                    val signupViewModel: SignupViewModel by viewModels { factory }
                    signupViewModel.registerAccount(name, email, password)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }


}