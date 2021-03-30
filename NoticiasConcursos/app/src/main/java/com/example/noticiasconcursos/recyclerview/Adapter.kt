package com.example.noticiasconcursos.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasconcursos.R


class Adapter(private val cardData: List<CardData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.cardViewTitulo)
        val decricao: TextView = itemView.findViewById(R.id.cardViewDescricao)
        val idNumber: TextView = itemView.findViewById(R.id.cardViewId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewObject = LayoutInflater.from(parent.context)
                .inflate(R.layout.noticia_card, parent, false)
        return ViewHolder(itemViewObject)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cardData[position]
        holder.titulo.text = currentItem.titulo
        holder.decricao.text = currentItem.descricao
        holder.idNumber.text = currentItem.id

    }

    override fun getItemCount() = cardData.size
    }
