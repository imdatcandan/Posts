package com.imdatcandan.posts

import com.imdatcandan.posts.data.PostRepository
import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.ui.viewmodel.PostListViewModel
import com.imdatcandan.posts.ui.viewmodel.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class PostListViewModelTest : BaseUnitTest<PostListViewModel>() {

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val postList = listOf(
        Post(1, 1, "asd", (1..100).joinToString { "A" }),
        Post(1, 1, "asd", (1..150).joinToString { "B" }),
        Post(1, 1, "asd", (1..120).joinToString { "C" })
    )

    private val trimmedPostList = listOf(
        Post(1, 1, "asd", (1..100).joinToString { "A" }),
        Post(1, 1, "asd", (1..120).joinToString { "B" }),
        Post(1, 1, "asd", (1..120).joinToString { "C" })
    )

    override fun initSelf(): PostListViewModel {
        return PostListViewModel(postRepository)
    }

    @Test
    fun `should fetch posts successfully`() = runTest(coroutineTestRule.testDispatcher) {

        coEvery {
            postRepository.getPostList()
        } returns postList


        tested.fetchPosts()

        assertEquals(
            UiState(data = trimmedPostList), tested.postListUiState.value
        )
    }


    @Test
    fun `should not fetch posts and show error dialog `() {
        coEvery {
            postRepository.getPostList()
        } throws Exception("error")


        tested.fetchPosts()

        assertEquals(UiState(data = null, error = "error"), tested.postListUiState.value)
    }


}