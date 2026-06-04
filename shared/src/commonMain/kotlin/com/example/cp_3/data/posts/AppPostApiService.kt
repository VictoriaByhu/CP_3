package com.example.cp_3.data.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.common.safeRequest
import com.example.cp_3.data.posts.model.responses.Posts
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType

internal class AppPostApiService(
    private val client: HttpClient
) : PostApiService {

    override suspend fun getAllPosts(): NetworkResult<Posts> {
        return client.safeRequest {
            get("$BASE_URL$POSTS_API") {
                accept(ContentType.Application.Json)
            }
        }
    }
}
