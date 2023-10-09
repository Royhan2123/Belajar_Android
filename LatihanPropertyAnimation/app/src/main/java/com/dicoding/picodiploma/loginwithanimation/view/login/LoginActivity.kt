package com.dicoding.picodiploma.loginwithanimation.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.loginwithanimation.view.ViewModelFactory
import com.dicoding.picodiploma.loginwithanimation.view.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }

    @SuppressLint("Recycle")
    private fun playAnimation(){
        // Set visibility
        binding.imageView.visibility = View.VISIBLE
        binding.messageTextView.visibility = View.VISIBLE
        binding.titleTextView.visibility = View.VISIBLE
        binding.emailTextView.visibility = View.VISIBLE
        binding.emailEditTextLayout.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
        binding.passwordTextView.visibility = View.VISIBLE
        binding.passwordEditTextLayout.visibility = View.VISIBLE
        binding.passwordEditText.visibility = View.VISIBLE
        binding.loginButton.visibility = View.VISIBLE


        binding.titleTextView.alpha = 0f
        binding.messageTextView.alpha = 0f
        binding.emailTextView.alpha = 0f
        binding.emailEditTextLayout.alpha = 0f
        binding.emailEditText.alpha = 0f
        binding.passwordTextView.alpha = 0f
        binding.passwordEditTextLayout.alpha = 0f
        binding.passwordEditText.alpha = 0f
        binding.loginButton.alpha = 0f

        // Create animator set
        val animatorSet = AnimatorSet()
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X,-50f,50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        animatorSet.playSequentially(
            ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailEditText, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordEditText, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500),
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
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            viewModel.saveSession(UserModel(email, "sample_token"))
            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
                setPositiveButton("Lanjut") { _, _ ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }
    }

}