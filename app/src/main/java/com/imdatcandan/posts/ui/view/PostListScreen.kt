package com.imdatcandan.posts.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imdatcandan.posts.R
import com.imdatcandan.posts.model.Post
import com.imdatcandan.posts.ui.component.ErrorDialog
import com.imdatcandan.posts.ui.component.ProgressBar
import com.imdatcandan.posts.ui.component.TopBar
import com.imdatcandan.posts.ui.viewmodel.PostListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
internal fun PostListScreen(
    viewModel: PostListViewModel = getViewModel(),
    onActionClick: (Int) -> Unit
) {
    viewModel.fetchPosts()
    TopBar(title = R.string.posts) {
        observeUiStateChanges(viewModel, onActionClick)
    }
}

@Composable
fun PostList(posts: List<Post>, onActionClick: (Int) -> Unit) {
    LazyColumn {
        items(posts) { post ->
            Row(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onActionClick.invoke(post.id)
                }) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(post.title, fontStyle = FontStyle.Italic, fontSize = 24.sp)
                    Text(post.body)
                }
            }
        }
    }
}

@Composable
private fun observeUiStateChanges(
    viewModel: PostListViewModel,
    onActionClick: (Int) -> Unit
) {
    val uiState = viewModel.postListUiState.value

    when {
        uiState.data != null -> {
            val posts = uiState.data
            PostList(posts, onActionClick)
        }
        uiState.error.isNotBlank() -> {
            if (uiState.isRetryRequired) {
                ErrorDialog(message = uiState.error) {
                    viewModel.fetchPosts()
                }
            } else {
                ErrorDialog(message = uiState.error) {
                }
            }
        }
        uiState.isLoading -> {
            ProgressBar()
        }
    }
}