package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.model.User

interface UserRepository {
    suspend fun getUserDetail(id: Int): User
}