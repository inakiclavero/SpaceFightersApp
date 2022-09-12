package com.example.gameapp.client.login

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("success") var success: String,
    @SerializedName("status code") var statuscode: String,
    @SerializedName("message") var message: String,
    @SerializedName("token") var token: String
)