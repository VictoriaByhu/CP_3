package com.example.cp_3.domain.posts.remove

import com.example.cp_3.data.common.Result
import com.example.cp_3.domain.posts.PostRepository

internal class RemovePostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: Int): Result<String> {
        return postRepository.deletePost(postId)
    }
}