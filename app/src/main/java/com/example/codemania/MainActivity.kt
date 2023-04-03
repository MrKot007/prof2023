package com.example.codemania

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activity.root)
        activity.imageView3.setOnClickListener {
            api.signOut(ModelToken(getSharedPreferences("savetoken", Context.MODE_PRIVATE)
                .getString("token", "no token").toString())).push(object: OnGetData<Boolean>{
                override fun onGet(data: Boolean) {
                    Toast.makeText(this@MainActivity, "Вы успешно вышли из аккаунта!", Toast.LENGTH_LONG).show()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@MainActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                }

            }, this)
        }
    }
}