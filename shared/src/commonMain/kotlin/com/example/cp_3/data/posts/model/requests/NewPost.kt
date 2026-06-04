package com.example.cp_3.data.posts.model.requests

import com.example.cp_3.data.posts.model.responses.Reactions
import kotlinx.serialization.Serializable

@Serializable
internal data class NewPost(
    val body: String = "",
    val reactions: Reactions,
    val tags: List<String> = emptyList(),
    val title: String = "",
    val userId: Int,
    val views: Int = 0
)
