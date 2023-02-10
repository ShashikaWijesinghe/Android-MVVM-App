package com.test.androidmvvmapp.presentation.post_detail

import com.test.androidmvvmapp.domain.model.Comment

data class CommentsListState(
    var isLoading: Boolean = false,
    var comments: List<Comment> = emptyList(),
    val error: String = ""
)
