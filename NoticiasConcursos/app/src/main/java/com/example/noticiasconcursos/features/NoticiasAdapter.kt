package com.example.noticiasconcursos.features

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.databinding.NoticiaCardBinding
import com.example.noticiasconcursos.view.DescriptionFragment
import com.squareup.picasso.Picasso


class NoticiasAdapter
    : ListAdapter<CardData, NoticiasAdapter.ViewHolder>(NoticiasComparator()) {


    class ViewHolder(private val binding: NoticiaCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var navController : NavController


        fun bindView(cardData: CardData) {
            binding.apply {
                cardViewTitulo.text = cardData.titulo
                Picasso.get().load(cardData.image).into(cardViewImage)
            }
        }

        init {
            itemView.setOnClickListener { v: View ->
                NoticiasViewModel.position = adapterPosition
                Log.e("clicked", adapterPosition.toString())
                //val activity = v.context as AppCompatActivity
                navController = Navigation.findNavController(v)//pega a referencia pro navController pela view, que é a inflada no layout atual

                navController.navigate(R.id.action_noticiasFragment_to_descriptionFragment)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoticiaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindView(currentItem)
        }
    }


    class NoticiasComparator : DiffUtil.ItemCallback<CardData>() {
        override fun areItemsTheSame(oldItem: CardData, newItem: CardData) =
            oldItem.titulo == newItem.titulo


        override fun areContentsTheSame(oldItem: CardData, newItem: CardData) =
            oldItem == newItem
    }
}

data class CardData(var titulo: String, var descricao: String, var image: String)



