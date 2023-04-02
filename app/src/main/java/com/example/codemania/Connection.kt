package com.example.codemania

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.codemania.Connection.retrofit
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connection {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://strukov-artemii.online:8085/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(API::class.java)
}
fun <T> Call<T>.push(onGetData: OnGetData<T>, context: Context) {
    enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.code() == 200) {
                if (response.body() == null) {
                    onGetData.onGet(response.body()!!)
                }else{
                    onGetData.onError("body null")
                }
            }else{
                Toast.makeText(context, "Ошибка ${response.code()}", Toast.LENGTH_LONG).show()
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.e("New error", t.message.toString())
        }

    })
}
interface OnGetData<T>{
    fun onGet(data: T)
    fun onError(error: String)
}