package com.example.gameapp.client.update

import com.example.gameapp.client.profile.UserProfile


data class UpdateResult (
    val success: UserProfile? = null,
    val error: Int? = null)