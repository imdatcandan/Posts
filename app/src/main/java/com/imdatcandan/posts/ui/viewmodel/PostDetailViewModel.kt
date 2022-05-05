package com.imdatcandan.posts.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdatcandan.posts.data.PostRepository
import com.imdatcandan.posts.data.UserRepository
import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.model.PostDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.min

class PostDetailViewModel(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {

    private val _postDetailUiState = mutableStateOf(UiState<PostDetail>())
    val postDetailUiState: State<UiState<PostDetail>> = _postDetailUiState

    fun getPostDetail(id: Int) {
        viewModelScope.launch {
            _postDetailUiState.value = UiState(isLoading = true)
            try {
                val post = postRepository.getPostDetail(id)
                val user = userRepository.getUserDetail(post.userId)

                val postDetail = PostDetail(post.id, post.userId, post.title, post.body, user.name, user.username)
                _postDetailUiState.value = UiState(data = postDetail)
            } catch (exception: Exception) {
                _postDetailUiState.value = UiState(error = exception.message ?: "Error!", isRetryRequired = true)
            }
        }
    }

}