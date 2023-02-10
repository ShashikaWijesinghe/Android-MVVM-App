package com.test.androidmvvmapp.data.repository

import android.util.Log
import com.test.androidmvvmapp.data.local.LocalDataSource
import com.test.androidmvvmapp.data.remote.PostApi
import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import com.test.androidmvvmapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi,
    private val localDataSource: LocalDataSource
) : PostRepository {

    private val TAG = "PostRepositoryImpl"

    override suspend fun getPosts(): Flow<List<Post>> {
        Log.i(TAG, "getPosts:")
        return flow {
            //load saved data from the DB
            val localPosts = localDataSource.getPosts()
            Log.i(TAG, "getPosts: localPosts = ${localPosts.size}")

            //emit local data
            if(localPosts.isNotEmpty()) {
                emit(localPosts)
            }

            try {
                //load remote data from the API
                val remotePosts = postApi.getPosts()
                Log.i(TAG, "getPosts: remotePosts = ${remotePosts.size}")

                //update the data to the DB
                localDataSource.insertPosts(remotePosts)

                //emit new data
                emit(remotePosts)
            } catch (e: Exception) {
                Log.i(TAG, "getPosts: error =  ${e.message}")
                if(localPosts.isEmpty()) {
                    emit(emptyList())
                }
            }
        }
    }

    override suspend fun getCommentsForPost(postId: Int): Flow<List<Comment>> {
        Log.i(TAG, "getCommentsForPost: postId = $postId")
        return flow {
            //load saved data from the DB
            val localComments = localDataSource.getCommentsForPost(postId)
            Log.i(TAG, "getCommentsForPost: localComments = ${localComments.size}")

            //emit local data
            if(localComments.isNotEmpty()) {
                emit(localComments)
            }

            try {
                //load remote data from the API
                val remoteComments = postApi.getComments(postId)
                Log.i(TAG, "getCommentsForPost: remoteComments = ${remoteComments.size}")

                //update the data to the DB
                localDataSource.insertComments(remoteComments)

                //emit new data
                emit(remoteComments)
            } catch (e: Exception) {
                Log.i(TAG, "getCommentsForPost: error =  ${e.message}")
                if(localComments.isEmpty()) {
                    emit(emptyList())
                }
            }
        }
    }
}