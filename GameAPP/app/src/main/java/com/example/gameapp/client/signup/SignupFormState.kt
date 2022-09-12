package com.example.gameapp.client.signup



data class SignupFormState (
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val first_nameError: Int? = null,
    val last_nameError: Int? = null,
    val ageError: Int? = null,
    val isDataValid: Boolean = true

)