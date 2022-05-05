package com.imdatcandan.posts.ui.viewmodel

data class UiState<out T : Any> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = "",
    val isRetryRequired: Boolean = false
)