package com.imdatcandan.posts.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imdatcandan.posts.ui.navigation.Screen
import com.imdatcandan.posts.ui.theme.PostsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.PostListScreen.route
                    ) {
                        addPostListGraph(navController)
                        addPostDetailGraph()
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.addPostListGraph(navController: NavController) {
        composable(
            route = Screen.PostListScreen.route
        ) {
            PostListScreen(onActionClick = { postId ->
                navController.navigate("posts/$postId")
            })
        }
    }

    private fun NavGraphBuilder.addPostDetailGraph() {
        composable(
            route = Screen.PostDetailScreen.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("postId")?.let {
                PostDetailScreen(it)
            }
        }
    }
}
