package com.test.androidmvvmapp.data.mapper

import com.test.androidmvvmapp.data.local.entity.CommentEntity
import com.test.androidmvvmapp.data.local.entity.PostEntity
import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post

fun Post.toPostEntity() : PostEntity {
    return PostEntity(
        id = id,
        body = body,
        title = title,
        userId = userId
    )
}

fun PostEntity.toPost() : Post {
    return Post(
        id = id,
        body = body,
        title = title,
        userId = userId
    )
}

fun CommentEntity.toComment() : Comment {
    return Comment(
        id = id,
        body = body,
        email = email,
        name = name,
        postId = postId
    )
}

fun Comment.toCommentEntity() : CommentEntity {
    return CommentEntity(
        id = id,
        body = body,
        email = email,
        name = name,
        postId = postId
    )
}