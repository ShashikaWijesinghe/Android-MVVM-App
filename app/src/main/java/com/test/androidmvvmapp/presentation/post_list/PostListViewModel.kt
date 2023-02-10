package com.test.androidmvvmapp.presentation.post_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.androidmvvmapp.domain.model.Post
import com.test.androidmvvmapp.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
   private var postRepository: PostRepository
) : ViewModel() {

    private val TAG = "PostListViewModel"

    private val _allPosts = mutableListOf<Post>()

    private val _postListState = mutableStateOf(PostListState())
    var postListState: State<PostListState> = _postListState

    init {
        getPosts()
    }

    private fun getPosts() {
        //Log.i(TAG, "getPosts:")
        viewModelScope.launch {
            try {
                _allPosts.clear()
                _postListState.value = PostListState(isLoading = true)
                postRepository.getPosts().collect { posts ->
                    //Log.i(TAG, "getPosts: posts = ${posts.size}")
                    _allPosts.clear()
                    _allPosts.addAll(posts)
                    _postListState.value = PostListState(posts = posts)
                }
            } catch (e: Exception) {
                _postListState.value = PostListState(error = e.message.orEmpty())
            }
        }
    }

    fun filterPosts(searchKey: String) {
        //Log.i(TAG, "filterPosts: searchKey = $searchKey")
        _postListState.value = PostListState(posts = _allPosts.filter {
            it.title.contains(searchKey, true)
                    || it.body.contains(searchKey, true)
        })
    }

    fun clearPostFilters() {
        //Log.i(TAG, "clearPostFilters:")
        _postListState.value = PostListState(posts = _allPosts)
    }

}