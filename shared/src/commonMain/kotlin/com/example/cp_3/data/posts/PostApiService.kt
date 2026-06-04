package com.example.cp_3.data.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.posts.model.requests.NewPost
import com.example.cp_3.data.posts.model.responses.Post
import com.example.cp_3.data.posts.model.responses.Posts

internal const val BASE_URL = "https://dummyjson.com/"
internal const val POSTS_API = "posts"
internal const val ADD_POST = "add"

internal interface PostApiService {
    suspend fun getAllPosts(): NetworkResult<Posts>
    suspend fun addPost(post: NewPost): NetworkResult<Post>
}
