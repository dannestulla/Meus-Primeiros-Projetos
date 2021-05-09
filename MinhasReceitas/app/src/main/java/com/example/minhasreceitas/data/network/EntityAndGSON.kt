package com.example.minhasreceitas.data.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class RecipesList(
    val meals: List<Meal>
)
@Entity(tableName = "recipes")
data class Meal(
        @ColumnInfo(name = "id", defaultValue = "")@PrimaryKey val idMeal: String,
        @ColumnInfo(name = "name", defaultValue = "")  val strMeal: String,
        @ColumnInfo(name = "image", defaultValue = "") val strMealThumb: String,
        @ColumnInfo(name = "recipe", defaultValue = "") val strInstructions: String,
        @ColumnInfo(name = "cuisine", defaultValue = "") var strArea : String
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
