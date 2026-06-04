package com.example.cp_3.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.example.cp_3.domain.posts.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Stable
class AppViewModel internal constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    internal val state: StateFlow<AppState> = _state.asStateFlow()
}
