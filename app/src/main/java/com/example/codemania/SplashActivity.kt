package com.example.codemania

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivitySplashBinding
import kotlin.properties.Delegates

class SplashActivity : AppCompatActivity() {
    private lateinit var activity: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (SharedPref.checkNotFirstEnter(this)) {
            SharedPref.saveNotFirstEnter(this)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                finish()
            }, 1000)
        }else{
            api.signIn(ModelAuth(SharedPref.getEmail(this).toString(), SharedPref.getPassword(this).toString()))
                .push(object: OnGetData<ModelIdentity> {
                    override fun onGet(data: ModelIdentity) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }

                    override fun onError(error: String) {
                        startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                        Log.e("splashError", error)
                    }

                }, this)
        }
    }
}