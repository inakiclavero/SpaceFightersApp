package com.example.gameapp.client.signup

import com.google.gson.annotations.SerializedName

data class UserSignupResponse (
    @SerializedName("success") var success: String,
    @SerializedName("status code") var statuscode: String,
    @SerializedName("message") var message: String
)