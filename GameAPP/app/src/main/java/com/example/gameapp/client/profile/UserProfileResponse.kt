package com.example.gameapp.client.profile

import com.example.gameapp.client.profile.UserProfile
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class UserProfileResponse (
    @SerializedName("success") var success: String,
    @SerializedName("status code") var statuscode: String,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<UserProfile>
        )