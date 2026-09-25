package com.example.cp_3.domain.posts.create

import com.example.cp_3.data.common.Result
import com.example.cp_3.data.posts.model.requests.NewPost
import com.example.cp_3.domain.posts.PostRepository

internal class CreatePostUseCase (
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(post: NewPost): Result<String> {
        return postRepository.addPost(post)
    }
}