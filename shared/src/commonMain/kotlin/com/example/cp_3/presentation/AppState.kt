package com.example.cp_3.presentation

import androidx.compose.runtime.Immutable
import com.example.cp_3.data.posts.model.responses.Post

@Immutable
internal data class AppState(
    val isProgressVisible: Boolean = false,
    val posts: List<Post> = emptyList(),
    val result: String? = null,
    val error: String? = null,
)
