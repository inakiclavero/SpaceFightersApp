package com.example.gameapp.client.login

/**
 * User details post authentication that is exposed to the UI
 */
data class  LoggedInUserView(
    val displayName: String,
    val tocken: String
)