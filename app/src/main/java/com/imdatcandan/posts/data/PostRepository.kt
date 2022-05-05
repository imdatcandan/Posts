package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post

interface PostRepository {
    suspend fun getPostList(): List<Post>
    suspend fun getPostDetail(id: Int): Post
}