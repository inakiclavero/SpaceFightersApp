package com.example.gameapp.ui


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.MainActivity
import com.example.gameapp.R as r
import android.R
import android.content.res.Configuration
import com.example.gameapp.databinding.ActivitySettingsBinding
import com.example.gameapp.prefs
import java.util.*

class SettingsActivity: AppCompatActivity(){

    private lateinit var binding: ActivitySettingsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var locale = Locale("en")
        val config =  getBaseContext().getResources().getConfiguration()
        var pos = 0
        var language ="en"
        val cmbOpciones = binding.cmbOpciones
        val languages = binding.language

        val datos = arrayOf("Easy", "Normal", "Hard")
        val lang = arrayOf("en", "es")
        val adaptador =
            ArrayAdapter(this, R.layout.simple_spinner_item, datos)

        val adaptador1 =
            ArrayAdapter(this, R.layout.simple_spinner_item, lang)

        adaptador.setDropDownViewResource(
            R.layout.simple_spinner_dropdown_item
        )

        adaptador1.setDropDownViewResource(
            R.layout.simple_spinner_dropdown_item
        )

        cmbOpciones.adapter = adaptador
        languages.adapter = adaptador1

        cmbOpciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>) {
                prefs.Dificulty = 0
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos = position
            }

        }


        languages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>) {
                prefs.Lang = "en"
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){
                    language= "en"
                } else if (position==1){
                language= "es"
                }
            }

        }










        binding.imageButtonBack?.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
            finish()
        }

        binding.save.setOnClickListener {
            val result = StringBuilder()
            if (binding.soundCheck.isChecked) {
                prefs.Sound=1
            } else {
                prefs.Sound=0
            }
            if (binding.vibrationCheck.isChecked) {
                prefs.Vibration=1
            } else {
                prefs.Vibration=0
            }

            prefs.Dificulty = pos

            prefs.Lang = language

            config.setLocale(Locale(prefs.Lang))

            getResources().updateConfiguration(config, null)


            result.append("Settings saved")
            startActivity(Intent(this, MainActivity::class.java))

            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
            finish()
        }
    }








    private fun showDialog() {
        val b = AlertDialog.Builder(this)
        var locale = Locale("en")
        val config =  getBaseContext().getResources().getConfiguration()
        b.setTitle(getResources().getString(r.string.lang))

        val types: Array<String> = getResources().getStringArray(r.array.languages)
        b.setItems(types, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dialog.dismiss()
                when (which) {
                    0 -> {
                        locale = Locale("en")

                        config.locale = locale
                    }
                    1 -> {
                        locale = Locale("es")
                        config.locale = locale
                    }
                    2 -> {
                        locale = Locale("pt")
                        config.locale = locale
                    }
                }
                getResources().updateConfiguration(config, null)
                startActivity(getIntent())
                finish()
            }
        })
        b.show()
    }

    override fun onStart() {
        super.onStart()

        val extras = intent.extras

        val Sound: Int? = extras?.getInt("Sound")
        val Vibration: Int? = extras?.getInt("Vibration")


        if (Sound != null) {
            binding.soundCheck.setChecked( Sound == 1 )
        }

        if (Vibration != null) {
            binding.vibrationCheck.setChecked(Vibration == 1)
        }

        Log.d("////////SSSSSSSSSSSSSS//////////",Sound.toString() + Vibration.toString())

    }

}