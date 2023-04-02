package com.example.codemania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.codemania.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var activity: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
        }, 1000)
    }
}