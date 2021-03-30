package com.example.myfavouritestracks.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavouritestracks.R


class CustomAdapter(private val exampleList: List<CardData>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//inicia com um construtor que é do tipo array do CardData

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //É uma blueprint de card, conecta os items do card às variáveis e as deixa prontas para serem "infladas/criadas"
        val songView: TextView = itemView.findViewById(R.id.song_name)
        val playView: ImageView = itemView.findViewById(R.id.play_button)


    }

    override fun onCreateViewHolder(viewGroup : ViewGroup, viewType: Int): ViewHolder {
        //chamado pelo Recyclerview quando for hora de criar um novo card/Viewholder
        //É preciso criar um objeto do layout. Para isso é usado o "inflate"
        val itemViewObject = LayoutInflater.from(viewGroup.context).inflate(R.layout.music_card, viewGroup, false)
        return ViewHolder(itemViewObject)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //relaciona o item à sua posição
        val currentItem = exampleList[position]
        holder.songView.text = currentItem.song_name
        holder.playView.tag = currentItem.tag_matcher_group

        //holder.artistView.text = currentItem.artist_name
        //holder.previewView.text = currentItem.preview

        // TODO: 11/03/2021 Implementar o preview
    }

    override fun getItemCount() = exampleList.size


}