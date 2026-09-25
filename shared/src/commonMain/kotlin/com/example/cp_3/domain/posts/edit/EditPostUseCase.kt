package com.example.cp_3.domain.posts.edit

import com.example.cp_3.data.common.Result
import com.example.cp_3.data.posts.model.responses.Post
import com.example.cp_3.domain.posts.PostRepository

internal class EditPostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(post: Post): Result<String> {
        return postRepository.updatePost(post)
    }
}