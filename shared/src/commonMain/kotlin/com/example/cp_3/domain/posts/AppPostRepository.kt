package com.example.cp_3.domain.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.posts.PostApiService
import com.example.cp_3.data.posts.model.responses.Posts

internal class AppPostRepository(
    private val postApiService: PostApiService
) : PostRepository {

    override suspend fun getAllPosts(): NetworkResult<Posts> {
        return postApiService.getAllPosts()
    }
}
