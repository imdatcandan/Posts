package com.imdatcandan.posts

import com.imdatcandan.posts.data.PostRepositoryImpl
import com.imdatcandan.posts.data.PostService
import com.imdatcandan.posts.model.Post
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class PostRepositoryTest : BaseUnitTest<PostRepositoryImpl>() {

    private val postService: PostService = mockk(relaxed = true)
    private val postList: List<Post> = mockk(relaxed = true)
    private val post = Post(1, 1, "asd", (1..100).joinToString("") { "A" })

    override fun initSelf() = PostRepositoryImpl(postService, coroutineTestRule.dispatcher)

    @Test
    fun `should get posts from service`() = testCoroutine {
        coEvery { postService.getPostList() } returns postList

        val result = tested.getPostList()

        assertEquals(result, postList)
    }

    @Test
    fun `should get post detail from service`() = testCoroutine {
        coEvery { postService.getPostDetail(1) } returns post

        val result = tested.getPostDetail(1)

        assertEquals(result, post)
    }
}