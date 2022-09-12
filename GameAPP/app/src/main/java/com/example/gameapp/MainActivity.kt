package com.example.gameapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.gameapp.client.ApiURL
import com.example.gameapp.databinding.ActivityMainBinding
import com.example.gameapp.ui.LoginActivity
import com.example.gameapp.ui.ProfileActivity
import com.example.gameapp.ui.SettingsActivity
import com.unity3d.player.UnityPlayerActivity
import java.util.*


class  MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var res: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val config =  getBaseContext().getResources().getConfiguration()

        config.setLocale(Locale(prefs.Lang))

        getResources().updateConfiguration(config, null)

        binding = ActivityMainBinding.inflate(layoutInflater)


        val myIntentLogin = Intent(this, LoginActivity::class.java)
        val myIntentProfile = Intent(this, ProfileActivity::class.java)

        

        binding.buttonLogin.setOnClickListener {
            if (prefs.TockenPref == "") {
                this.startActivity(myIntentLogin)
            } else {
                prefs.TockenPref = ""
                prefs.UsernamePref = ""
                binding.buttonLogin.setImageResource(R.drawable.icons8_login_96___);
                binding.textViewLogin.setText("LOGIN")
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

        }



        binding.buttonPlay.setOnClickListener {
            val unityActivity = Intent(this, UnityPlayerActivity::class.java)
            unityActivity.putExtra("Token", prefs.TockenPref)

            unityActivity.putExtra("Sound", prefs.Sound)
            unityActivity.putExtra("Vibration", prefs.Vibration)
            unityActivity.putExtra("Dificulty", prefs.Dificulty)
            unityActivity.putExtra("Url", ApiURL().appURL)


            this.startActivity(unityActivity)

        }

        binding.buttonSettings.setOnClickListener{

            val settingsActivity = Intent(this, SettingsActivity::class.java)
            settingsActivity.putExtra("Sound", prefs.Sound)
            settingsActivity.putExtra("Vibration", prefs.Vibration)
            settingsActivity.putExtra("Dificulty", prefs.Dificulty)
            this.startActivity(settingsActivity)

        }



        binding.buttonStats.setOnClickListener {
            if (prefs.TockenPref == "") {
                Toast.makeText(
                    applicationContext,
                    "Inicia Sesión",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                this.startActivity(myIntentProfile)
            }

        }



            setContentView(binding.root)
        }

    override fun  onBackPressed()
    {
        finish()
    }



    override fun onStart() {
        super.onStart()


        val myIntent = Intent(this, LoginActivity::class.java)

            if (prefs.TockenPref != "") {
                binding.buttonLogin.setImageResource(R.drawable.icons8_logout_rounded_down_96___);
                //buttonMyText.setTextColor(Color.BLACK);
                binding.textViewLogin.setText("LOGOUT")
            } else {
                binding.textViewLogin.setText("LOGIN")

            }

        Log.d("//////////////////",prefs.Sound.toString() + prefs.Vibration.toString() + prefs.Dificulty)



    }

}