package com.test.androidmvvmapp.presentation

sealed class Screen(val route: String) {
    object PostListScreen : Screen("post_list_screen")
    object PostDetailScreen : Screen("post_detail_screen")
}
