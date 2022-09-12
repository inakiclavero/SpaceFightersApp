package com.example.gameapp.client


import com.example.gameapp.client.login.UserLoginResponse
import com.example.gameapp.client.profile.UserProfileResponse
import com.example.gameapp.client.profile.UserProfilesResponse
import com.example.gameapp.client.signup.UserSignupResponse
import com.example.gameapp.client.login.UserCred
import com.example.gameapp.client.signup.UserReg
import com.example.gameapp.client.update.UserUpdateProfile
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    @POST("/api/signup")
    suspend fun signup(@Body userReg: UserReg): Response<UserSignupResponse>


    @GET("/api/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<UserProfileResponse>


    @GET("/api/allprofiles")
    suspend fun getProfiles(@Header("Authorization") token: String): Response<UserProfilesResponse>


    @POST("/api/signin")
    suspend fun login(@Body userCred: UserCred): Response<UserLoginResponse>



    @PUT("/api/update")
    suspend fun update(@Header("Authorization") token: String,@Body profile: UserUpdateProfile): Response<UserProfileResponse>
}

