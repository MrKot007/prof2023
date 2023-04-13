package com.example.session2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    @GET("catalog/tags")
    fun getTags() : Call<List<ModelTag>>
    @GET("catalog/courses")
    fun getCourses() : Call<List<ModelCourse>>
    @POST("media")
    fun getImage(@Header("filename") filename: String) : Call<ResponseBody>
}
