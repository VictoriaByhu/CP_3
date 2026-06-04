package com.example.cp_3.domain.posts

import com.example.cp_3.data.posts.PostApiService

internal class AppPostRepository(
    private val postApiService: PostApiService
) : PostRepository
