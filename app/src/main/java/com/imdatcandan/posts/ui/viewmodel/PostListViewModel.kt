package com.imdatcandan.posts.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdatcandan.posts.data.PostRepository
import com.imdatcandan.posts.model.Post
import kotlinx.coroutines.launch
import kotlin.math.min

class PostListViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _postListUiState = mutableStateOf(UiState<List<Post>>())
    val postListUiState: State<UiState<List<Post>>> = _postListUiState

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _postListUiState.value = UiState(isLoading = true)
            try {
                val posts = postRepository.getPostList()
                val subPosts = posts.map {
                    it.copy(
                        body = limitBodyByMaxCharacter(it.body)
                    )
                }
                _postListUiState.value = UiState(data = subPosts)
            } catch (exception: Exception) {
                _postListUiState.value =
                    UiState(error = exception.message ?: "Error!", isRetryRequired = true)
            }
        }
    }

    private fun limitBodyByMaxCharacter(body: String): String {
        return body.substring(MIN_CHARACTER_LIMIT, min(MAX_CHARACTER_LIMIT, body.length))
    }

    private companion object {
        private const val MIN_CHARACTER_LIMIT = 0
        private const val MAX_CHARACTER_LIMIT = 120
    }
}