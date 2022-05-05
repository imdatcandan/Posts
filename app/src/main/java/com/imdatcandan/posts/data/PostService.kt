package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    suspend fun getPostList(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostDetail(@Path("id") id: Int): Post
}