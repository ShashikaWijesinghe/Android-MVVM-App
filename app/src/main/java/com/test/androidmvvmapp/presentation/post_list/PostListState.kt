package com.test.androidmvvmapp.presentation.post_list

import com.test.androidmvvmapp.domain.model.Post

data class PostListState(
    var isLoading: Boolean = false,
    var posts: List<Post> = emptyList(),
    val error: String = ""
)
