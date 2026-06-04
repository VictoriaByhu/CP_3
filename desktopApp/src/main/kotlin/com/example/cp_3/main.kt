package com.example.cp_3

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.cp_3.di.initKoin
import com.example.cp_3.presentation.App

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "CP_3",
    ) {
        App()
    }
}
