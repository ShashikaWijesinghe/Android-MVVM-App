package com.test.androidmvvmapp

import com.test.androidmvvmapp.domain.repository.PostRepository
import com.test.androidmvvmapp.presentation.post_list.PostListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class PostListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var postListViewModel: PostListViewModel
    private lateinit var postRepository: PostRepository

    @Before
    fun setup() {
        postRepository = FakePostRepository()
        postListViewModel = PostListViewModel(postRepository)
    }

    @Test
    fun test_get_posts() = runTest {
        //need to wait until getPosts() task is completed
        advanceUntilIdle()

        val postListState = postListViewModel.postListState

        Assert.assertEquals(5, postListState.value.posts.size)
        Assert.assertEquals(1, postListState.value.posts[0].id)
    }

    @Test
    fun test_filter_posts() = runTest {
        //need to wait until getPosts() task is completed
        advanceUntilIdle()

        postListViewModel.filterPosts("body3")

        val postListState = postListViewModel.postListState
        Assert.assertEquals(1, postListState.value.posts.size)
        Assert.assertEquals(3, postListState.value.posts[0].id)
    }

    @Test
    fun test_clear_filters() = runTest {
        //need to wait until getPosts() task is completed
        advanceUntilIdle()

        val postListState = postListViewModel.postListState

        postListViewModel.filterPosts("body3")
        Assert.assertEquals(1, postListState.value.posts.size)

        postListViewModel.clearPostFilters()
        Assert.assertEquals(5, postListState.value.posts.size)
    }
}