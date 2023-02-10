package com.test.androidmvvmapp.presentation.post_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.androidmvvmapp.domain.model.Comment

@Composable
fun CommentListItem(
    comment: Comment
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "${comment.id}. ${comment.name}",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = comment.body,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = comment.email,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}