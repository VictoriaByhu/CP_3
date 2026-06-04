package com.example.cp_3.data.posts

import com.example.cp_3.data.common.NetworkResult
import com.example.cp_3.data.common.safeRequest
import com.example.cp_3.data.posts.model.requests.NewPost
import com.example.cp_3.data.posts.model.responses.Post
import com.example.cp_3.data.posts.model.responses.Posts
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

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

    override suspend fun addPost(post: NewPost): NetworkResult<Post> {
        return client.safeRequest {
            post("$BASE_URL$POSTS_API/$ADD_POST") {
                contentType(ContentType.Application.Json)
                setBody(post)
            }
        }
    }
}
