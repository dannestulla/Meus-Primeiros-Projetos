package com.example.minhasreceitas.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minhasreceitas.R
import com.example.minhasreceitas.data.remote.Meal
import com.example.minhasreceitas.databinding.RecipeCardHorizontalBinding
import com.example.minhasreceitas.presenter.viewmodel.InstructionsViewModel.Companion.currentMeal
import com.example.minhasreceitas.presenter.viewmodel.RecipesListViewModel
import com.squareup.picasso.Picasso

class RecipesAdapter(
    //private val listener: OnItemClickListener
) :
    ListAdapter<Meal, RecipesAdapter.RecipesAdapterViewHolder>(ReceitasComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterViewHolder {
        val binding =
            RecipeCardHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesAdapterViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindView(currentItem)
        }
    }

    class RecipesAdapterViewHolder(private val binding: RecipeCardHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(meal: Meal) {
            binding.apply {
                name.text = meal.strMeal
                Picasso.get().load(meal.strMealThumb).into(imageView25)
            }
        }
        init {
            itemView.setOnClickListener { v: View ->
                currentMeal = RecipesListViewModel.mealsList[adapterPosition]
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

