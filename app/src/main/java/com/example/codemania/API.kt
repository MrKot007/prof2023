package com.example.codemania

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("signIn")
    fun signIn(@Body body: ModelAuth) : Call<ModelIdentity>
    @POST("signUp")
    fun signUp(@Body body: ModelReg) : Call<ModelIdentity>
}