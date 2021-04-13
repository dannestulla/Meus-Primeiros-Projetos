package com.example.noticiasconcursos.features

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.view.DescriptionFragment
import com.example.noticiasconcursos.databinding.NoticiaCardBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.InternalCoroutinesApi



class NoticiasAdapter : ListAdapter<CardData, NoticiasAdapter.ViewHolder>(NoticiasComparator()) {


    class ViewHolder(private val binding: NoticiaCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(cardData :CardData) {
            binding.apply {
                cardViewTitulo.text = cardData.titulo
                //cardViewDescricao.text = cardData.descricao
                Picasso.get().load(cardData.image).into(cardViewImage);
            }
        }
        //Click Listener para cada opção
        init {
            itemView.setOnClickListener { v: View ->
                NoticiasViewModel.position = adapterPosition
                Log.e("clicked", adapterPosition.toString())
                val activity = v.context as AppCompatActivity
                val newFragment = activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DescriptionFragment()).addToBackStack(null)
                newFragment.commit()
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

data class CardData(var titulo : String, var descricao : String, var image : String)



