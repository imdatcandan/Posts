package com.imdatcandan.posts.ui.navigation

sealed class Screen(val route: String) {
    object PostListScreen : Screen("posts")
    object PostDetailScreen : Screen("posts/{postId}")
}