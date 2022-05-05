package com.imdatcandan.posts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.imdatcandan.posts.ui.theme.PostsTheme
import com.imdatcandan.posts.ui.view.PostListScreen
import org.junit.Rule
import org.junit.Test

class PostListUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun postListUiTest() {
        composeTestRule.setContent {
            PostsTheme {
                PostListScreen(onActionClick = {})
            }
        }
        composeTestRule.onNodeWithText("Posts").assertIsDisplayed()
    }
}