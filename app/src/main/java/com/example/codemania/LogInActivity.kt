package com.example.codemania

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = binding.mailInp.text.toString()
        val pass = binding.passInp.text.toString()
        if (email == "") {
            Toast.makeText(this@LogInActivity, "Введите почту!", Toast.LENGTH_LONG).show()
        }
        if (pass == "") {
            Toast.makeText(this@LogInActivity, "Введите пароль!", Toast.LENGTH_LONG).show()
        }
        if (!checkMail(email)) {
            Toast.makeText(this@LogInActivity, "Почта указана неверно!", Toast.LENGTH_LONG).show()
        }
        api.signIn(ModelAuth(email, pass)).push(object: OnGetData<ModelIdentity>{
            override fun onGet(data: ModelIdentity) {
                Info.token = data.token
                Info.firstname = data.user.firstname
                Info.lastname = data.user.lastname
                Info.patronymic = data.user.patronymic
                Info.avatar = data.user.avatar
                getSharedPreferences("savetoken", Context.MODE_PRIVATE).edit().putString("token", data.token).apply()
                getSharedPreferences("savemail", Context.MODE_PRIVATE).edit().putString("mail", email).apply()
                getSharedPreferences("savepassword", Context.MODE_PRIVATE).edit().putString("pass", pass).apply()
                startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                finish()
            }

            override fun onError(error: String) {
                Toast.makeText(this@LogInActivity, "error", Toast.LENGTH_LONG).show()
            }

        },this)
    }
    fun checkMail(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}