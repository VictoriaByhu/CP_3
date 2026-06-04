package com.example.cp_3.data.posts

import io.ktor.client.HttpClient

internal class AppPostApiService(
    private val client: HttpClient
) : PostApiService
