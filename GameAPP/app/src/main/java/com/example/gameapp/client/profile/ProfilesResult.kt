package com.example.gameapp.client.profile


data class ProfilesResult (
    val success:MutableList<ProfileUserView>? = null,
    val error: Int? = null
)