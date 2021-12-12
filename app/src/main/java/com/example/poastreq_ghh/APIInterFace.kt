package com.example.poastreq_ghh


import retrofit2.Call
import retrofit2.http.*

interface APIInterFace {
    @GET("?format=json")
    fun getUsers(): Call<User>


    @POST("?format=json")
    fun setUsers(@Body data: UserItem) :  Call<UserItem>
}