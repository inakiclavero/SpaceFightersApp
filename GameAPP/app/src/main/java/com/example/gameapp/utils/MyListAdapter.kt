package com.example.gameapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.gameapp.R
import com.example.gameapp.client.profile.ProfileResult

class MyListAdapter(private val mContext: Context, private val profiles: MutableList<ProfileResult>)
    : ArrayAdapter<ProfileResult>(mContext,0,profiles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false)



        val profile = profiles[position]



        val nameText = layout.findViewById(R.id.nombre) as TextView
        val scoreText = layout.findViewById(R.id.score) as TextView
        val timeText = layout.findViewById(R.id.time) as TextView


        nameText.text = profiles[position].success?.first_name



            scoreText.text  = profile.success?.best_score.toString() + " points"
            timeText.text = profile.success?.games.toString() + " games"


        return layout
    }
}