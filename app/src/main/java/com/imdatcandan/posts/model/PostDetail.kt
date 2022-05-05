package com.imdatcandan.posts.model

data class PostDetail(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val name: String,
    val userName: String,
)