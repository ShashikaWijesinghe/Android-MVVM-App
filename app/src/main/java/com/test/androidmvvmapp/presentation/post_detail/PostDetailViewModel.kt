package com.test.androidmvvmapp.presentation.post_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import com.test.androidmvvmapp.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private var postRepository: PostRepository
): ViewModel() {

    private val TAG = "PostDetailViewModel"

    private val _allComments = mutableListOf<Comment>()

    private val _selectedPostState = mutableStateOf(PostDetailState())
    var selectedPostState: State<PostDetailState> = _selectedPostState

    private val _commentsListState = mutableStateOf(CommentsListState())
    var commentsListState: State<CommentsListState> = _commentsListState

    fun updateSelectedPost(post: Post) {
        _selectedPostState.value = PostDetailState(post)
        getCommentsForPost(post)
    }

    private fun getCommentsForPost(post: Post) {
        Log.i(TAG, "getCommentsForPost: postId = ${post.id}")
        viewModelScope.launch {
            try {
                _allComments.clear()
                _commentsListState.value = CommentsListState(isLoading = true)
                postRepository.getCommentsForPost(post.id).collect { comments ->
                    Log.i(TAG, "getCommentsForPost: comments = ${comments.size}")
                    _allComments.clear()
                    _allComments.addAll(comments)
                    _commentsListState.value = CommentsListState(comments = comments)
                }
            } catch (e: Exception) {
                _commentsListState.value = CommentsListState(error = e.message.orEmpty())
            }
        }
    }

    fun filterComments(searchKey: String) {
        Log.i(TAG, "filterComments: searchKey = $searchKey")
        _commentsListState.value = CommentsListState(comments = _allComments.filter {
            it.name.contains(searchKey, true)
                    || it.body.contains(searchKey, true)
                    || it.email.contains(searchKey, true)
        })
    }

    fun clearCommentFilters() {
        Log.i(TAG, "clearCommentFilters:")
        _commentsListState.value = CommentsListState(comments = _allComments)
    }
}