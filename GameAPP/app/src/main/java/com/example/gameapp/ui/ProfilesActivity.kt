package com.example.gameapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.MainActivity
import com.example.gameapp.R
import com.example.gameapp.client.profile.ProfileResult
import com.example.gameapp.client.profile.ProfileUserView
import com.example.gameapp.client.profile.ProfileViewModel
import com.example.gameapp.databinding.ActivityProfileBinding
import com.example.gameapp.databinding.ActivityProfilesBinding
import com.example.gameapp.prefs
import com.example.gameapp.utils.CheckConnection
import com.example.gameapp.utils.MyListAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor


class ProfilesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfilesBinding
    lateinit var profile: LiveData<ProfileResult>
    //lateinit var profiles: MutableList<ProfileResult>
    private lateinit var chart: BarChart
    private var seekBarX: SeekBar? = null
    private  var seekBarY:SeekBar? = null
    private var tvX: TextView? = null
    private  var tvY:TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilesBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_profiles)

        val list1=findViewById<ListView>(R.id.listView)

        var profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.imageButtonBack.setOnClickListener{
            val myIntent = Intent(this, ProfileActivity::class.java)
            this.startActivity(myIntent)
        }





        binding = ActivityProfilesBinding.inflate(layoutInflater)
        //setContentView(binding.root)

       // val recipeList = binding.recipeListView



        profileViewModel.profilesResult.observe(this, Observer {
            val profilesResult = it ?: return@Observer



            if (profilesResult[0].error != null) {
                profilesResult[0].error?.let { it1 -> showProfileFailed(it1) }
            }
            if (profilesResult[0].success != null) {
                profilesResult[0].success?.let { it1 -> updateUiWithUser(it1) }


                /*
                var list: MutableList<String> = ArrayList()
                for (p in profilesResult){
                    if (p.success?.games!! >0){
                        list.add("Name: "+ p.success.first_name +"          Score/Game: " + (p.success.score / p.success.games).toString()+"          Time/Game: " + (p.success.time / p.success.games).toString())
                    }else{
                        list.add("Name: "+ p.success.first_name +"          Score/Game: 0          Time/Game: 0")
                    }



                }*/
                val myListAdapter = MyListAdapter(this,profilesResult)
                Log.d("///////////////////////////////////////////////////////profileeeeee", profilesResult[0].success.toString())
                list1.adapter = myListAdapter //ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,list)









            }
            setResult(RESULT_OK)
        })


            profileViewModel.getprofiles(prefs.TockenPref)










        setResult(RESULT_OK)



    }

    override fun onStart() {
        super.onStart()

        binding = ActivityProfilesBinding.inflate(layoutInflater)





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
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }



}
