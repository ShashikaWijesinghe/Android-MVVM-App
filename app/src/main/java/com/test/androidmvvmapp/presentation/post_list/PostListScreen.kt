package com.test.androidmvvmapp.presentation.post_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.androidmvvmapp.R
import com.test.androidmvvmapp.presentation.Screen
import com.test.androidmvvmapp.presentation.common.SearchBar
import com.test.androidmvvmapp.presentation.post_detail.PostDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun PostListScreen(
    navController: NavController,
    postListViewModel: PostListViewModel,
    postDetailViewModel: PostDetailViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        //search bar
        SearchBar(
            hint = stringResource(R.string.txt_search_posts),
            onValueChanged = {
                postListViewModel.filterPosts(it)
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
            },
            onClearText = {
                postListViewModel.clearPostFilters()
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
            }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            //post list
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = listState,
                contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            ) {
                items(postListViewModel.postListState.value.posts) { post ->
                    PostListItem(
                        post = post,
                        onItemClick = {
                            postDetailViewModel.updateSelectedPost(post)
                            navController.navigate(Screen.PostDetailScreen.route)
                        }
                    )
                }
            }

            //progress bar
            if (postListViewModel.postListState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if(postListViewModel.postListState.value.error.isNotEmpty()) {
                Text(
                    text = postListViewModel.postListState.value.error,
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                )
            }
        }
    }


}