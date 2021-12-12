package com.example.poastreq_ghh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private var retrofitVar: Retrofit? = null

    fun getClient():Retrofit?{
        retrofitVar =  Retrofit.Builder()
                .baseUrl("https://dojo-recipes.herokuapp.com/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofitVar
    }
}