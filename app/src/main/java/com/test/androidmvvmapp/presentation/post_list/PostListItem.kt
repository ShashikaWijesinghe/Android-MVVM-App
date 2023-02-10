package com.test.androidmvvmapp.presentation.post_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.androidmvvmapp.domain.model.Post

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostListItem(
    post: Post,
    onItemClick: (Post) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onItemClick(post)
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "${post.id}. ${post.title}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.body2
            )
        }
    }

}