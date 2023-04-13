package com.example.codemania

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codemania.Connection.api
import com.example.codemania.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var sbinding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sbinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(sbinding.root)
        sbinding.goToCreateAccount.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LogInActivity::class.java))
            finish()
        }
        sbinding.enter.setOnClickListener {
            val bio = listOf<String>(
                sbinding.lastnameInp.text.toString(),
                sbinding.firstnameInp.text.toString(),
                sbinding.patronymicInp.text.toString(),
                sbinding.sexInp.text.toString(),
                sbinding.birthInp.text.toString(),
                sbinding.mailInp.text.toString(),
                sbinding.passwordInp.text.toString(),
                sbinding.repeatpasswordInp.toString()
            )
            bio.map {
                if (it == "") {
                    Toast.makeText(this@SignUpActivity, "Заполните все поля!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            if (bio[3] != "Мужской" || bio[3] != "Женский") {
                Toast.makeText(this@SignUpActivity, "Пол указан неверно!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
//            if (bio[6].toString() != bio[7].toString()) {
//                Toast.makeText(this@SignUpActivity, "Пароли не совпадают!", Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }
            api.signUp(ModelReg(bio[1], bio[0], bio[2], bio[5], bio[6], bio[4], bio[3])).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    Info.token = data.token
                    Info.firstname = data.user.firstname
                    Info.lastname = data.user.lastname
                    Info.patronymic = data.user.patronymic
                    Info.avatar = data.user.avatar
                    SharedPref.saveToken(this@SignUpActivity, data.token)
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_LONG).show()
                }

            }, this)
        }

    }
}