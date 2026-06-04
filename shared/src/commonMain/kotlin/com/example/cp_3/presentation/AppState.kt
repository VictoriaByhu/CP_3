package com.example.cp_3.presentation

import androidx.compose.runtime.Immutable

@Immutable
internal data class AppState(
    val isProgressVisible: Boolean = false,
    val result: String? = null,
    val error: String? = null,
)
