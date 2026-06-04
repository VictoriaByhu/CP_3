package com.example.cp_3.domain.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.posts.model.responses.Posts

internal interface PostRepository {
    suspend fun getAllPosts(): NetworkResult<Posts>
}
