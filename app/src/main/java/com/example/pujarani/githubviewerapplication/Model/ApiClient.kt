package com.example.pujarani.githubviewerapplication.Model

import android.provider.ContactsContract
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

/**
 * Created by Puja.Rani on 14-04-2020.
 */
object ApiClient {

    private val API_BASE_URL = "https://api.github.com/search/"

    private var servicesApiInterface:apiInterface?=null

    fun build():apiInterface?{

        var builder : Retrofit.Builder = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(apiInterface::class.java)

        return servicesApiInterface as apiInterface

    }

    private fun interceptor(): HttpLoggingInterceptor{
        var httpLogingInterceptor = HttpLoggingInterceptor()
        httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLogingInterceptor
    }

    /*interface apiInterface{
        @GET("/users?q=tom+repos:%3E42+followers:%3E1000/")
        fun getList(): Call<List<DataResponse>>
    }*/

    interface apiInterface{
        @GET("/users?q=tom+repos:%3E42+followers:%3E1000/")
        fun getList(): Call<List<GitHubDatModel>>
    }
}