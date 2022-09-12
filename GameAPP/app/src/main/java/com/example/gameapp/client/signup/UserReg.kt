package com.example.gameapp.client.signup

import com.example.gameapp.client.profile.UserProfile


data class UserReg(
    val email:String,
    val password:String,
    val profile: UserProfile
)

