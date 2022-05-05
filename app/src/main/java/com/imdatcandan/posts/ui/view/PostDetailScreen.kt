package com.imdatcandan.posts.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.imdatcandan.posts.R
import com.imdatcandan.posts.model.PostDetail
import com.imdatcandan.posts.ui.component.ErrorDialog
import com.imdatcandan.posts.ui.component.ProgressBar
import com.imdatcandan.posts.ui.component.TopBar
import com.imdatcandan.posts.ui.viewmodel.PostDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
internal fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel = getViewModel(),
) {
    viewModel.getPostDetail(postId)

    TopBar(title = R.string.post_detail) {
        observeUiStateChanges(viewModel, postId)
    }
}

@Composable
fun PostDetail(postDetail: PostDetail) {
    Column {
        Text(
            "Title= ${postDetail.title}", fontStyle = FontStyle.Italic, fontSize = 24.sp
        )
        Text("Body= ${postDetail.body}")
        Text("Name= ${postDetail.name}")
        Text("Username= ${postDetail.userName}")
    }
}

@Composable
private fun observeUiStateChanges(
    viewModel: PostDetailViewModel,
    postId: Int
) {
    val uiState = viewModel.postDetailUiState.value

    when {
        uiState.data != null -> {
            val postDetail = uiState.data
            PostDetail(postDetail)
        }
        uiState.error.isNotBlank() -> {
            if (uiState.isRetryRequired) {
                ErrorDialog(message = uiState.error) {
                    viewModel.getPostDetail(postId)
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