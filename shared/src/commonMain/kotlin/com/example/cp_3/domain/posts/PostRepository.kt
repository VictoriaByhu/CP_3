package com.example.cp_3.domain.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.posts.model.requests.NewPost
import com.example.cp_3.data.posts.model.responses.Post
import com.example.cp_3.data.posts.model.responses.Posts

internal interface PostRepository {
    suspend fun getAllPosts(): NetworkResult<Posts>
    suspend fun addPost(post: NewPost): NetworkResult<Post>
    suspend fun updatePost(post: Post): NetworkResult<Post>
}
