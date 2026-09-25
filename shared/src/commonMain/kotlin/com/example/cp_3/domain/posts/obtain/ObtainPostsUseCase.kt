package com.example.cp_3.domain.posts.obtain

import com.example.cp_3.data.common.Result
import com.example.cp_3.data.posts.model.responses.Posts
import com.example.cp_3.domain.posts.PostRepository

internal class ObtainPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Result<Posts> {
        return postRepository.getAllPosts()
    }
}