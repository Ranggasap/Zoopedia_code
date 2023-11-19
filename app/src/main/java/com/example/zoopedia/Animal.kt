package com.example.zoopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val name: String,
    val description: String,
    val photo: Int,
    val scientificName: String,
    val animalFoodName1: String,
    val animalFoodPhoto1: Int,
    val animalFoodName2: String,
    val animalFoodPhoto2: Int,
    val animalFoodName3: String,
    val animalFoodPhoto3 : Int,
    val animalFunfact: String,
    val sourceInfo: String
): Parcelable
