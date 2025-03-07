package com.newstartup.music

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val context:Activity, private val dataList: List<Data>):
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create the view in case the layout manager fails to create view for the data
        val itemView= LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // populate the data into the view

        // Get the current data item
        val currentData = dataList[position]

        // Initialize MediaPlayer with preview URL
        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())

        // Set text
        holder.Title.text= currentData.title

        // Artist text
        holder.Artist.text= currentData.artist.name

        // Load image using Picasso
        Picasso.get().load(currentData.album.cover).into(holder.Image)

        holder.Play.setOnClickListener {
            mediaPlayer.start()
        }

        holder.Pause.setOnClickListener {
            mediaPlayer.stop()
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val Image: ImageView
        val Title: TextView
        val Artist: TextView
        val Play: ImageButton
        val Pause: ImageButton

        init {
            Image = itemView.findViewById(R.id.musicImage)
            Title = itemView.findViewById(R.id.musicTitle)
            Play = itemView.findViewById(R.id.playButton)
            Pause = itemView.findViewById(R.id.pauseButton)
            Artist = itemView.findViewById(R.id.musicArtist)
        }
    }

}