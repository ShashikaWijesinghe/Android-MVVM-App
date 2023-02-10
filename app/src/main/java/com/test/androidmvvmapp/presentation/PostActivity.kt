package com.test.androidmvvmapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.androidmvvmapp.presentation.post_detail.PostDetailScreen
import com.test.androidmvvmapp.presentation.post_detail.PostDetailViewModel
import com.test.androidmvvmapp.presentation.post_list.PostListScreen
import com.test.androidmvvmapp.presentation.post_list.PostListViewModel
import com.test.androidmvvmapp.presentation.ui.theme.AndroidMVVMAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val postListViewModel = hiltViewModel<PostListViewModel>()
            val postDetailViewModel = hiltViewModel<PostDetailViewModel>()
            val navController = rememberNavController()

            AndroidMVVMAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PostListScreen.route
                    ){
                        composable(
                            route = Screen.PostListScreen.route
                        ) {
                            PostListScreen(navController, postListViewModel, postDetailViewModel)
                        }

                        composable(
                            route = Screen.PostDetailScreen.route
                        ) {
                            PostDetailScreen(navController, postDetailViewModel)
                        }
                    }
                }
            }
        }
    }
}

