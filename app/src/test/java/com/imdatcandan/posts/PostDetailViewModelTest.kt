package com.imdatcandan.posts

import com.imdatcandan.posts.data.PostRepository
import com.imdatcandan.posts.data.UserRepository
import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.model.PostDetail
import com.imdatcandan.posts.model.User
import com.imdatcandan.posts.ui.viewmodel.PostDetailViewModel
import com.imdatcandan.posts.ui.viewmodel.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDetailViewModelTest : BaseUnitTest<PostDetailViewModel>() {

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val userRepository: UserRepository = mockk(relaxed = true)

    private val post = Post(1, 1, "asd", (1..100).joinToString("") { "A" })
    private val user = User(1, "ali", "can")
    private val postDetail =
        PostDetail(post.userId, post.id, post.title, post.body, user.name, user.username)

    override fun initSelf() = PostDetailViewModel(postRepository, userRepository)

    @Test
    fun `should get post detail successfully`() = testCoroutine {

        coEvery {
            postRepository.getPostDetail(1)
        } returns post
        coEvery {
            userRepository.getUserDetail(1)
        } returns user

        tested.getPostDetail(1)

        assertEquals(
            UiState(data = postDetail), tested.postDetailUiState.value
        )
    }


    @Test
    fun `should not get post detail and show error dialog `() = testCoroutine {
        coEvery {
            postRepository.getPostDetail(1)
        } throws Exception("error")

        tested.getPostDetail(1)

        assertEquals(
            UiState(data = null, error = "error", isRetryRequired = true),
            tested.postDetailUiState.value
        )
    }
}