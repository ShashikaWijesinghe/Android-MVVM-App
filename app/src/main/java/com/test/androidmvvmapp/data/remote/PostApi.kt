package com.test.androidmvvmapp.data.remote

import com.test.androidmvvmapp.domain.model.Comment
import com.test.androidmvvmapp.domain.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("/posts")
    suspend fun getPosts() : List<Post>

    @GET("/posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int) : List<Comment>

}