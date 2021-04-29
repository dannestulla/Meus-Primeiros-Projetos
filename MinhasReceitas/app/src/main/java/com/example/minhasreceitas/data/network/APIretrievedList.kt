package com.example.minhasreceitas.data.network

import androidx.room.Entity
import androidx.room.PrimaryKey


data class RecipesList(
    val meals: List<Meal>
)
@Entity(tableName = "recipes")
data class Meal(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val recipe: String,
    val strInstructions: String,
    val strYoutube : String,
    val strCategory :  String,
    val strArea : String,

)

data class DescriptionRecipe(
    val meals: List<Meal>
)

/*
data class Recipe(
    val strInstructions: String,
    val strYoutube : String,
    val strCategory :  String,
    val strArea : String,
    val strMeal : String
)*/
