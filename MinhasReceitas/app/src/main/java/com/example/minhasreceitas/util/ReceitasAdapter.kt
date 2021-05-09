package com.example.minhasreceitas.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minhasreceitas.R
import com.example.minhasreceitas.viewmodels.ReceitasViewModel
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.databinding.RecipeCardHorizontalBinding
import com.squareup.picasso.Picasso

class ReceitasAdapter :
        ListAdapter<Meal, ReceitasAdapter.ReceitasViewHolder>(ReceitasComparator()){

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitasViewHolder {
        val binding =
                RecipeCardHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReceitasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceitasViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindView(currentItem)
        }
    }

    class ReceitasViewHolder(private val binding: RecipeCardHorizontalBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bindView(meal : Meal) {
                binding.apply {
                    name.text = meal.strMeal
                    Picasso.get().load(meal.strMealThumb).into(imageView25)
                }
            }
        init {
            itemView.setOnClickListener { v:View ->
                ReceitasViewModel.currentMeal.add(ReceitasViewModel.mealsList[adapterPosition])
                val navController = Navigation.findNavController(v)
                navController.navigate(R.id.action_recipeFragment_to_descriptionFragment)

            }
        }
    }
    class ReceitasComparator : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal) =
                oldItem.idMeal == newItem.idMeal

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal) =
                oldItem == newItem
    }
}

