package com.example.gameapp.client.update


data class UpdateFormState (
    val first_nameError: Int? = null,
    val last_nameError: Int? = null,
    val ageError: Int? = null,
    val isDataValid: Boolean = true

)