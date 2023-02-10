package com.test.androidmvvmapp.data.local

import com.test.androidmvvmapp.data.mapper.toComment
import com.test.androidmvvmapp.data.mapper.toCommentEntity
import com.test.androidmvvmapp.data.mapper.toPost
import com.test.androidmvvmapp.data.mapper.toPostEntity
import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val postDatabase: PostDatabase
) {
    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        postDatabase.postDao().getPosts().map { it.toPost() }
    }

    suspend fun insertPosts(posts: List<Post>) {
        withContext(Dispatchers.IO) {
            postDatabase.postDao().insertAll(posts.map { it.toPostEntity() })
        }
    }

    suspend fun getCommentsForPost(postId: Int): List<Comment> = withContext(Dispatchers.IO) {
        postDatabase.commentsDao().getCommentsForPost(postId).map { it.toComment() }
    }

    suspend fun insertComments(comments: List<Comment>) {
        withContext(Dispatchers.IO) {
            postDatabase.commentsDao().insertAll(comments.map { it.toCommentEntity() })
        }
    }
}