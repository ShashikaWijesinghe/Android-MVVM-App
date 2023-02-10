package com.test.androidmvvmapp.presentation.post_detail

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.androidmvvmapp.R
import com.test.androidmvvmapp.presentation.common.SearchBar
import kotlinx.coroutines.launch

@Composable
fun PostDetailScreen(
    navController: NavController,
    viewModel: PostDetailViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        viewModel.selectedPostState.value.post?.let { post ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = "${post.id}. ${post.title}",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        //search bar
        SearchBar(
            hint = stringResource(R.string.txt_search_comments),
            onValueChanged = {
                viewModel.filterComments(it)
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
            },
            onClearText = {
                viewModel.clearCommentFilters()
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
            }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = listState,
                contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            ) {

                //comments
                items(viewModel.commentsListState.value.comments){ comment ->
                    CommentListItem(comment = comment)
                }
            }

            //progress bar
            if (viewModel.commentsListState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }


}