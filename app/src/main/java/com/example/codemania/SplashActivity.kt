package com.example.codemania

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivitySplashBinding
import kotlin.properties.Delegates
var sh: SharedPreferences? = null
class SplashActivity : AppCompatActivity() {
    private lateinit var activity: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sh = getSharedPreferences("saveenter", Context.MODE_PRIVATE)
        if (sh!!.getBoolean("isFirstEnter", false)) {
            saveNotFirstEnter()
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                finish()
            }, 1000)
        }else{
            api.signIn(ModelAuth(getSharedPreferences("savemail", Context.MODE_PRIVATE).getString("mail", "no mail").toString(),
            getSharedPreferences("savepassword", Context.MODE_PRIVATE).getString("password", "no password").toString()))
                .push(object: OnGetData<ModelIdentity> {
                    override fun onGet(data: ModelIdentity) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }

                    override fun onError(error: String) {
                        startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                    }

                }, this)
        }
    }
    fun saveNotFirstEnter() {
        sh!!.edit().putBoolean("isFirstEnter", false).apply()
    }
}