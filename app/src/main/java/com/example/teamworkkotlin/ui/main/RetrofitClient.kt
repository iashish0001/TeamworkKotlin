package com.example.teamworkkotlin.ui.main


import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList


interface RetrofitClient {
    @get:GET("photos")
    val `object`: Call<ArrayList<Any?>?>?
}
