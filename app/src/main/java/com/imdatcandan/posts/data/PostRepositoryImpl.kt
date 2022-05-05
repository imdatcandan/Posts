package com.imdatcandan.posts.data

import com.imdatcandan.posts.model.Post
import kotlinx.coroutines.*

class PostRepositoryImpl(
    private val postService: PostService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostRepository {

    override suspend fun getPostList(): List<Post> = withContext(ioDispatcher) {
        return@withContext postService.getPostList()
    }

    override suspend fun getPostDetail(id: Int): Post = withContext(ioDispatcher) {
        return@withContext postService.getPostDetail(id)
    }
}
