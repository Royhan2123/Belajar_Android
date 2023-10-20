package com.example.tugassubmission.Ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.tugassubmission.Model.LoginViewModel
import com.example.tugassubmission.R
import com.example.tugassubmission.data.remote.ApiResponse
import com.example.tugassubmission.data.remote.auth.LoginBody
import com.example.tugassubmission.databinding.ActivityLoginBinding
import com.example.tugassubmission.ext.ConstVal.KEY_EMAIL
import com.example.tugassubmission.ext.ConstVal.KEY_IS_LOGIN
import com.example.tugassubmission.ext.ConstVal.KEY_TOKEN
import com.example.tugassubmission.ext.ConstVal.KEY_USER_ID
import com.example.tugassubmission.ext.ConstVal.KEY_USER_NAME
import com.example.tugassubmission.ext.SessionManager
import com.example.tugassubmission.ext.gone
import com.example.tugassubmission.ext.show
import com.example.tugassubmission.ext.showOKDialog
import com.example.tugassubmission.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    private var _activityLoginBinding: ActivityLoginBinding? = null
    private val binding get() = _activityLoginBinding!!

    private lateinit var pref: SessionManager

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_activityLoginBinding?.root)

        pref = SessionManager(this)

        initAction()
        playAnimation()
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


    private fun initAction() {
        binding.btnLogin.setOnClickListener {
            val userEmail = binding.edtEmail.text.toString()
            val userPassword = binding.edtPassword.text.toString()

            when {
                userEmail.isBlank() -> {
                    binding.edtEmail.requestFocus()
                    binding.edtEmail.error = getString(R.string.PasswordKosong)
                }
                userPassword.isBlank() -> {
                    binding.edtPassword.requestFocus()
                    binding.edtPassword.error = getString(R.string.PasswordKosong)
                }
                else -> {
                    val request = LoginBody(
                        userEmail, userPassword
                    )

                    loginUser(request, userEmail)
                }
            }
        }
        binding.tvToRegister.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    private fun loginUser(loginBody: LoginBody, email: String) {
        loginViewModel.loginUser(loginBody).observe(this) { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    try {
                        showLoading(false)
                        val userData = response.data.loginResult
                        pref.apply {
                            setStringPreference(KEY_USER_ID, userData.userId)
                            setStringPreference(KEY_TOKEN, userData.token)
                            setStringPreference(KEY_USER_NAME, userData.name)
                            setStringPreference(KEY_EMAIL, email)
                            setBooleanPreference(KEY_IS_LOGIN, true)
                        }
                    } finally {
                        MainActivity.start(this)
                    }
                }
                is ApiResponse.Error -> {
                    showLoading(false)
                    showOKDialog(getString(R.string.Kesalahan), getString(R.string.message_incorrect_auth))
                }
                else -> {
                    showToast(getString(R.string.message_unknown_state))
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.show() else binding.progressBar.gone()
        binding.edtEmail.isClickable = !isLoading
        binding.edtEmail.isEnabled = !isLoading
        binding.edtPassword.isClickable = !isLoading
        binding.edtPassword.isEnabled = !isLoading
        binding.btnLogin.isClickable = !isLoading
    }
}