package com.example.gameapp.client.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gameapp.R
import com.example.gameapp.client.APIService
import com.example.gameapp.client.ApiURL
import com.example.gameapp.client.login.LoginFormState
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ProfileViewModel: ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _profileResult = MutableLiveData<ProfileResult>()
    val profileResult: LiveData<ProfileResult> = _profileResult

    private val _profilesResult: MutableLiveData<MutableList<ProfileResult>> = MutableLiveData<MutableList<ProfileResult>>()
    val profilesResult: MutableLiveData<MutableList<ProfileResult>> = _profilesResult

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiURL().appURL)
            .addConverterFactory( GsonConverterFactory.create())
            .build()
    }


    fun getprofile(token: String): LiveData<ProfileResult> {
        CoroutineScope(Dispatchers.IO).launch {
       try {

           val call = getRetrofit().create(APIService::class.java).getProfile("Bearer " + token)



           if (call.isSuccessful) {

               var gson = Gson()
               //var profile = gson.fromJson(call.body()?.data?.get(0).get(), UserProfile::class.java)

               _profileResult.postValue(ProfileResult(success = call.body()?.data?.get(0)?.let {
                   ProfileUserView(
                       first_name = it.first_name, last_name = it.last_name,
                       age = it.age, score = it.score, time = it.time, best_score = it.best_score,
                       last_score = it.last_score, last_time = it.last_time, games = it.games
                   )
               }))


           } else {
               _profileResult.postValue(ProfileResult(error = R.string.profile_failed))
               Log.d("MENSAJE LOGIN", call.body()?.data.toString())
           }

       } catch (e: Exception){
           _profileResult.postValue(ProfileResult(error = R.string.connection))
           Log.d("MENSAJE LOGIN", "Connection Failed")
        }


    }

        return _profileResult
    }



    fun getprofiles(token: String): MutableLiveData<MutableList<ProfileResult>> {
        CoroutineScope(Dispatchers.IO).launch {

            try {
            val call = getRetrofit().create(APIService::class.java).getProfiles("Bearer " + token)

            if (call.isSuccessful) {

                var gson = Gson()
                //var profile = gson.fromJson(call.body()?.data?.get(0).get(), UserProfile::class.java)
                var list: MutableList<ProfileResult> = ArrayList()
                for (x in 0..(call.body()?.data!!.size-1)){
                    list.add(ProfileResult(success = call.body()?.data?.get(x)?.let {
                        ProfileUserView(
                            first_name = it.first_name, last_name=it.last_name, age = it.age,
                            score = it.score, time = it.time,best_score=it.best_score, last_score = it.last_score,
                            last_time = it.last_time, games = it.games)
                    }))



                }
                _profilesResult.postValue(list)

/*
                val resultList: MutableList<ProfileUserView> = ArrayList()
                for (x in call.body()?.data!!){
                    resultList.add(ProfileUserView(
                        first_name = x.first_name, last_name=x.last_name,
                        phone_number = x.phone_number, age = x.age, gender = x.gender,
                        score = x.score, time = x.time, last_score = x.last_score,
                        last_time = x.last_time, games = x.games))
                }

                _profileResult.postValue(ProfilesResult(success = call.body()?.data?.let {
                    resultList
                }))
*/

            } else {
                _profileResult.postValue(ProfileResult(error = R.string.profile_failed))
                Log.d("MENSAJE LOGIN", call.body()?.data.toString())
            }
            } catch (e: Exception){
                _profileResult.postValue(ProfileResult(error = R.string.connection))
                Log.d("MENSAJE LOGIN", "Connection Failed")
            }

        }


        return _profilesResult
    }


}