package com.example.codemania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codemania.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activity.root)

    }
}