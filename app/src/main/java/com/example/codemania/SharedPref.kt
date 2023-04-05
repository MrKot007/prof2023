package com.example.codemania

import android.content.Context
import android.content.SharedPreferences

class SharedPref {
    companion object{
        fun setEmail(context: Context, email: String) {
            val sp: SharedPreferences = context.getSharedPreferences("SavingM", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("email", email).apply()
        }
        fun getEmail(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("SavingM", Context.MODE_PRIVATE)
            return sp.getString("email", "no mail found")
        }
        fun setPassword(context: Context, password: String) {
            val sp: SharedPreferences = context.getSharedPreferences("SavingP", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("password", password).apply()
        }
        fun getPassword(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("SavingP", Context.MODE_PRIVATE)
            return sp.getString("password", "no password set")
        }
        fun checkNotFirstEnter(context: Context): Boolean {
            val sp: SharedPreferences = context.getSharedPreferences("SavingE", Context.MODE_PRIVATE)
            return sp.getBoolean("isFirstEnter", true)
        }
        fun saveNotFirstEnter(context: Context) {
            val sp: SharedPreferences = context.getSharedPreferences("SavingE", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putBoolean("isFirstEnter", false).apply()
        }
        fun saveToken(context: Context, token: String) {
            val sp: SharedPreferences = context.getSharedPreferences("SavingT", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("token", token).apply()
        }
        fun getToken(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("SavingT", Context.MODE_PRIVATE)
            return sp.getString("token", "no token set")
        }
    }
}