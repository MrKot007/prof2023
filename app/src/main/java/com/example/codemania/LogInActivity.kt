package com.example.codemania

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var lbinding : ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lbinding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(lbinding.root)
        lbinding.goToCreateAccount.setOnClickListener {
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
            finish()
        }
        lbinding.enter.setOnClickListener {

            val email = lbinding.mailInp.text.toString()
            val pass = lbinding.passInp.text.toString()
            if (email == "") {
                Toast.makeText(this@LogInActivity, "Введите почту!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass == "") {
                Toast.makeText(this@LogInActivity, "Введите пароль!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!checkMail(email)) {
                Toast.makeText(this@LogInActivity, "Почта указана неверно!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            api.signIn(ModelAuth(email, pass)).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    Info.token = data.token
                    Info.userId = data.user.userId
                    Info.firstname = data.user.firstname
                    Info.lastname = data.user.lastname
                    Info.patronymic = data.user.patronymic
                    Info.avatar = data.user.avatar
                    SharedPref.setEmail(this@LogInActivity, email)
                    SharedPref.setPassword(this@LogInActivity, pass)
                    SharedPref.saveToken(this@LogInActivity, data.token)
                    startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@LogInActivity, error, Toast.LENGTH_LONG).show()
                }

            },this)
        }
    }
    fun checkMail(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}