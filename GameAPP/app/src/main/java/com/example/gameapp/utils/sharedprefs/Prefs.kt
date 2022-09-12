package com.example.gameapp.utils.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager





class Prefs (context: Context)
{

    private val PREF_USER_NAME = "username"

    private val PREF_USER_TOCKEN = "tocken"

    private val SCORE_GAME = "score"

    private val TIME_GAME = "time"

    private val LANG = "lang"

    private val SOUND = "sound"

    private val VIBRATION = "vibration"
    private val DIFICULTY = "dificulty"



    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)



    var UsernamePref: String
        get() = preferences.getString(PREF_USER_NAME,"")?:""
        set(value) = preferences.edit().putString(PREF_USER_NAME, value).apply()


    var TockenPref: String
        get() = preferences.getString(PREF_USER_TOCKEN,"")?:""
        set(value) = preferences.edit().putString(PREF_USER_TOCKEN, value).apply()

    var ScoreGame: String
        get() = preferences.getString(SCORE_GAME,"")?:""
        set(value) = preferences.edit().putString(SCORE_GAME, value).apply()

    var TimeGame: String
        get() = preferences.getString(TIME_GAME,"")?:""
        set(value) = preferences.edit().putString(TIME_GAME, value).apply()

    var Lang: String
        get() = preferences.getString(LANG,"")?:"en"
        set(value) = preferences.edit().putString(LANG, value).apply()

    var Sound: Int
        get() = preferences.getInt(SOUND,1)
        set(value) = preferences.edit().putInt(SOUND, value).apply()


    var Vibration: Int
        get() = preferences.getInt(VIBRATION,1)
        set(value) = preferences.edit().putInt(VIBRATION, value).apply()


    var Dificulty: Int
        get() = preferences.getInt(DIFICULTY,0)
        set(value) = preferences.edit().putInt(DIFICULTY, value).apply()



}