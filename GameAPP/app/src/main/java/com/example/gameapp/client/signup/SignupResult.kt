package com.example.gameapp.client.signup



data class SignupResult (
    val success: SignupUserView? = null,
    val error: Int? = null)