package com.example.codemania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codemania.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var activity: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }
}