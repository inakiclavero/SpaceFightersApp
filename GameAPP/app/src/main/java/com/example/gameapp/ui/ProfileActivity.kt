package com.example.gameapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.MainActivity
import com.example.gameapp.R
import com.example.gameapp.client.profile.ProfileResult
import com.example.gameapp.client.profile.ProfileUserView
import com.example.gameapp.client.profile.ProfileViewModel
import com.example.gameapp.databinding.ActivityProfileBinding
import com.example.gameapp.prefs
import com.example.gameapp.utils.CheckConnection


class ProfileActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    lateinit var profile: LiveData<ProfileResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)


        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val age = binding.age
        val firstName = binding.firstName
        val score = binding.score
        val time = binding.time
        val last_score = binding.lastScore
        val last_time = binding.lastTime
        val best_score = binding.bscore
        val score_game = binding.scoregame
        val time_game = binding.timegame



        binding.imageButtonBack.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.statsListButton.setOnClickListener {
            val pActivity = Intent(this, ProfilesActivity::class.java)
            this.startActivity(pActivity)

        }

        binding.editButton.setOnClickListener {
            val pActivity = Intent(this, UpdateProfileActivity::class.java)
            this.startActivity(pActivity)
        }



        profileViewModel.profileResult.observe(this, Observer {
            val profileResult = it ?: return@Observer

            if (profileResult.error != null) {

                showProfileFailed(profileResult.error)
            }
            if (profileResult.success != null) {
                updateUiWithUser(profileResult.success)
                /*
                    profile.value?.success?.let { age.setText(it.age.toString()) }
                    profile.value?.success?.let {
                        val name = it.first_name + " " + it.last_name;
                        if (name.length > 20 ){
                            firstName.setText(it.first_name)
                        } else {
                            firstName.setText(it.first_name + " " + it.last_name)
                        }


                        firstName.setText(it.first_name + " " + it.last_name) }
                    profile.value?.success?.let { score.setText(it.score.toString()) }
                    profile.value?.success?.let { time.setText((it.time/3600).toString() + " h" +
                            ((it.time % 3600) / 60).toString() + " min" + (it.time % 60).toString() + " sec") }
                    profile.value?.success?.let { last_score.setText(it.last_score.toString()) }
                    profile.value?.success?.let { last_time.setText(it.last_time.toString() + " seconds") }
                    profile.value?.success?.let {
                        if (it.games>0){
                            score_game.setText((it.score / it.games).toString())
                        } else {
                            score_game.setText(0.toString())
                        }
                    }
                    profile.value?.success?.let {
                        if (it.games>0){
                            time_game.setText((it.time / it.games).toString() + "seconds")
                        } else {
                            time_game.setText(0.toString() + "seconds")
                        }
                    }*/



                profile.value?.success?.let {

                    age.setText(it.age.toString())

                    val name = it.first_name + " " + it.last_name;
                    if (name.length > 17 ){
                        firstName.setText(it.first_name)
                    } else {
                        firstName.setText(it.first_name + " " + it.last_name)
                    }
                    score.setText(it.score.toString())
                    time.setText((it.time/3600).toInt().toString() + " h " +
                        ((it.time % 3600) / 60).toInt().toString() + " min " + (it.time % 60).toInt().toString() + " s ")
                    best_score.setText(it.best_score.toString())
                    last_score.setText(it.last_score.toString())
                    last_time.setText(it.last_time.toString() + " s")

                    if (it.games>0){
                        score_game.setText((it.score / it.games).toString())
                    } else {
                        score_game.setText(0.toString())
                    }


                    if (it.games>0){
                        time_game.setText((it.time / it.games).toString() + "s")
                        prefs.ScoreGame =(it.score / it.games).toString()
                        prefs.TimeGame =(it.time / it.games).toString()
                    } else {
                        time_game.setText(0.toString() + " s")
                        prefs.ScoreGame =(it.score / 1).toString()
                        prefs.TimeGame =(it.time / 1).toString()
                    }


                }
            }
            setResult(RESULT_OK)


            //Complete and destroy login activity once successful

        })

            profile = profileViewModel.getprofile(prefs.TockenPref)






    }

    override fun onStart() {
        super.onStart()

        binding = ActivityProfileBinding.inflate(layoutInflater)





    }

    private fun updateUiWithUser(model: ProfileUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.first_name
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showProfileFailed(@StringRes errorString: Int) {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

