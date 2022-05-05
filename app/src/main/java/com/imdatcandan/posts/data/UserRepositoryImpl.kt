package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userService: UserService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override suspend fun getUserDetail(id: Int): User  = withContext(ioDispatcher) {
        return@withContext userService.getUserDetail(id)
    }
}
