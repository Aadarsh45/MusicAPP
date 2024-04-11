package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//populate data
        val currData = dataList[position]

        //play &pause music
        var mediaPlayer = MediaPlayer.create(context,currData.preview.toUri())


        holder.title.text = currData.title
        Picasso.get().load(currData.album.cover).into(holder.image)

        holder.play.setOnClickListener{
           mediaPlayer.start()
        }
        holder.pause.setOnClickListener{
            mediaPlayer.pause()
        }
    }
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val play: ImageButton
        val pause : ImageButton
        init {
            image = itemView.findViewById(R.id.musicImg)
            title = itemView.findViewById(R.id.songName)
            play = itemView.findViewById(R.id.btnPlay)
            pause = itemView.findViewById(R.id.btnPause)
        }

    }
}

