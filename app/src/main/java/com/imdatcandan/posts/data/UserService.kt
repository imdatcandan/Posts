package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): User
}