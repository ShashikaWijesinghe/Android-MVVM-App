package com.test.androidmvvmapp

import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import com.test.androidmvvmapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePostRepository : PostRepository {

    override suspend fun getPosts(): Flow<List<Post>> {
        return flow {
            val posts = mutableListOf<Post>()
            posts.add(Post(1, "Test post body1", "test title1", 1))
            posts.add(Post(2, "Test post body2", "test title2", 1))
            posts.add(Post(3, "Test post body3", "test title3", 1))
            posts.add(Post(4, "Test post body4", "test title4", 2))
            posts.add(Post(5, "Test post body5", "test title5", 2))

            emit(posts)
        }
    }

    override suspend fun getCommentsForPost(postId: Int): Flow<List<Comment>> {
        return flow {

        }
    }
}