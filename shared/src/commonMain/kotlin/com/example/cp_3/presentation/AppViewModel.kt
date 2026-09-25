package com.example.cp_3.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp_3.data.common.onFailure
import com.example.cp_3.data.common.onSuccess
import com.example.cp_3.data.posts.model.requests.NewPost
import com.example.cp_3.data.posts.model.responses.Reactions
import com.example.cp_3.domain.posts.create.CreatePostUseCase
import com.example.cp_3.domain.posts.edit.EditPostUseCase
import com.example.cp_3.domain.posts.obtain.ObtainPostsUseCase
import com.example.cp_3.domain.posts.remove.RemovePostUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
class AppViewModel internal constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val obtainPostsUseCase: ObtainPostsUseCase,
    private val removePostUseCase: RemovePostUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    internal val state: StateFlow<AppState> = _state.asStateFlow()

    private val _events = Channel<AppEvent>(capacity = Channel.BUFFERED)
    val events: Flow<AppEvent> = _events.receiveAsFlow()

    init {
        fetchPosts()
    }

    fun onAction(action: AppAction) {
        when (action) {
            AppAction.OnFetchPosts -> fetchPosts()
            AppAction.OnCreatePost -> createPost()
            AppAction.OnUpdatePost -> updatePost()
            AppAction.OnDeletePost -> deletePost()
        }
    }

    private fun fetchPosts() = launchRequest {
        obtainPostsUseCase()
            .onSuccess { posts -> _state.update { it.copy(posts = posts.posts, result = posts.toString()) } }
            .onFailure { message -> _events.trySend(AppEvent.ShowGetErrorSnackbar(message)) }
    }

    private fun createPost() = launchRequest {
        createPostUseCase(createNewPost())
            .onSuccess { result -> _state.update { it.copy(result = result) } }
            .onFailure { message -> _events.trySend(AppEvent.ShowPostErrorSnackbar(message)) }
    }

    private fun updatePost() = launchRequest {
        val post = _state.value.posts.firstOrNull()
        if (post == null) {
            _events.trySend(AppEvent.ShowPutErrorSnackbar("Load posts before updating one."))
            return@launchRequest
        }

        editPostUseCase(post.copy(body = "Updated body"))
            .onSuccess { result -> _state.update { it.copy(result = result) } }
            .onFailure { message -> _events.trySend(AppEvent.ShowPutErrorSnackbar(message)) }
    }

    private fun deletePost() = launchRequest {
        val post = _state.value.posts.firstOrNull()
        if (post == null) {
            _events.trySend(AppEvent.ShowDeleteErrorSnackbar("Load posts before deleting one."))
            return@launchRequest
        }

        removePostUseCase(post.id)
            .onSuccess { result -> _state.update { it.copy(result = result) } }
            .onFailure { message -> _events.trySend(AppEvent.ShowDeleteErrorSnackbar(message)) }
    }

    private fun launchRequest(block: suspend () -> Unit) {
        viewModelScope.launch {
            resetPreviousResults()
            _state.update { it.copy(isProgressVisible = true) }
            try {
                block()
            } finally {
                _state.update { it.copy(isProgressVisible = false) }
            }
        }
    }

    private fun resetPreviousResults() {
        _state.update { it.copy(result = null, error = null) }
    }

    private fun createNewPost() = NewPost(
        body = "Body text",
        reactions = Reactions(),
        tags = listOf("Tag 1", "Tag 2"),
        title = "Title text",
        userId = 5,
    )
}
