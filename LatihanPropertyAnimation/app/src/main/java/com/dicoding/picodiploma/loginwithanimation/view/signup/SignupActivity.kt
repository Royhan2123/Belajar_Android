package com.dicoding.picodiploma.loginwithanimation.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }


    @SuppressLint("Recycle")
    private fun playAnimation(){
        // Set visibility
        binding.imageView.visibility = View.VISIBLE
        binding.titleTextView.visibility = View.VISIBLE
        binding.nameTextView.visibility = View.VISIBLE
        binding.nameEditTextLayout.visibility = View.VISIBLE
        binding.nameEditText.visibility = View.VISIBLE
        binding.emailTextView.visibility = View.VISIBLE
        binding.emailEditTextLayout.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
        binding.passwordTextView.visibility = View.VISIBLE
        binding.passwordEditTextLayout.visibility = View.VISIBLE
        binding.passwordEditText.visibility = View.VISIBLE
        binding.signupButton.visibility = View.VISIBLE

        binding.titleTextView.alpha = 0f
        binding.nameTextView.alpha = 0f
        binding.nameEditTextLayout.alpha = 0f
        binding.nameEditText.alpha = 0f
        binding.emailTextView.alpha = 0f
        binding.emailEditTextLayout.alpha = 0f
        binding.emailEditText.alpha = 0f
        binding.passwordTextView.alpha = 0f
        binding.passwordEditTextLayout.alpha = 0f
        binding.passwordEditText.alpha = 0f
        binding.signupButton.alpha = 0f

        // Create animator set
        val animatorSet = AnimatorSet()
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X,-50f,50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        animatorSet.playSequentially(
            ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.nameEditText, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.emailEditText, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.passwordEditText, View.ALPHA, 1f).setDuration(500),
            ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)
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
        binding.signupButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()

            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Akun dengan $email sudah jadi nih. Yuk, login dan belajar coding.")
                setPositiveButton("Lanjut") { _, _ ->
                    finish()
                }
                create()
                show()
            }
        }
    }
}