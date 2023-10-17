package com.example.tugassubmission

import android.annotation.SuppressLint
import android.media.session.MediaSessionManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var pref:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        pref = SessionManager(this)
        val isLogin = pref.isLogin
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                isLogin -> {
                    MainActivity.start(this)
                    finish()
                }
                else -> {
                    LoginActivity.start(this)
                    finish()
                }
            }
        }, UiConstValue.LOADING_TIME)
    }
}