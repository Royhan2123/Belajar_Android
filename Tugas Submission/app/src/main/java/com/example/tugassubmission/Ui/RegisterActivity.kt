package com.example.tugassubmission.Ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import com.example.tugassubmission.Model.RegisterViewModel
import com.example.tugassubmission.R
import com.example.tugassubmission.data.remote.ApiResponse
import com.example.tugassubmission.data.remote.auth.AuthBody
import com.example.tugassubmission.databinding.ActivityRegisterBinding
import com.example.tugassubmission.ext.UiConstValue
import com.example.tugassubmission.ext.isEmailValid
import com.example.tugassubmission.ext.showOKDialog
import com.example.tugassubmission.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()

    private var _activityRegisterBinding: ActivityRegisterBinding? = null
    private val binding get() = _activityRegisterBinding!!

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(_activityRegisterBinding?.root)

        initAction()
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



    private fun initAction() {
        binding.btnRegister.setOnClickListener {
            val userName = binding.edtName.text.toString()
            val userEmail = binding.edtEmail.text.toString()
            val userPassword = binding.edtPassword.text.toString()

            Handler(Looper.getMainLooper()).postDelayed({
                when {
                    userName.isBlank() -> binding.edtName.error = getString(R.string.Nama_Kosong)
                    userEmail.isBlank() -> binding.edtEmail.error = getString(R.string.EmailKosong)
                    !userEmail.isEmailValid() -> binding.edtEmail.error = getString(R.string.Email_Salah)
                    userPassword.isBlank() -> binding.edtPassword.error = getString(R.string.PasswordKosong)
                    else -> {
                        val request = AuthBody(
                            userName, userEmail, userPassword
                        )
                        registerUser(request)
                    }
                }
            }, UiConstValue.ACTION_DELAYED_TIME)
        }
        binding.tvToLogin.setOnClickListener {
            LoginActivity.start(this)
        }
    }

    private fun registerUser(newUser: AuthBody) {
        registerViewModel.registerUser(newUser).observe(this) { response ->
            when(response) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    try {
                        showLoading(false)
                    } finally {
                        LoginActivity.start(this)
                        finish()
                        showToast(getString(R.string.message_register_success))
                    }
                }
                is ApiResponse.Error -> {
                    showLoading(false)
                    showOKDialog(getString(R.string.Kesalahan), response.errorMessage)
                }
                else -> {
                    showToast(getString(R.string.message_unknown_state))
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.edtName.isClickable = !isLoading
        binding.edtName.isEnabled = !isLoading
        binding.edtEmail.isClickable = !isLoading
        binding.edtEmail.isEnabled = !isLoading
        binding.edtPassword.isClickable = !isLoading
        binding.edtPassword.isEnabled = !isLoading
        binding.btnRegister.isClickable = !isLoading
    }

}