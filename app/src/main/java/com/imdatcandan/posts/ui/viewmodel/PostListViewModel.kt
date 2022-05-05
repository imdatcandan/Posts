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

   /* init {
        fetchPosts()
    }*/

    fun fetchPosts() {
        viewModelScope.launch {
            _postListUiState.value = UiState(isLoading = true)
            try {
                val posts = postRepository.getPostList()
                val subPosts = posts.map {
                    it.copy(
                        body = it.body.substring(0, min(120, it.body.length))
                    )
                }
                _postListUiState.value = UiState(data = subPosts)
            } catch (exception: Exception) {
                _postListUiState.value =
                    UiState(error = exception.message ?: "Error!", isRetryRequired = true)
            }
        }
    }
}