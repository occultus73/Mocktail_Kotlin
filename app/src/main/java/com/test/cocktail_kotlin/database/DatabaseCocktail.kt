package com.test.cocktail_kotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.cocktail_kotlin.domain.Cocktail

@Entity
data class DatabaseCocktail(
    @PrimaryKey
    var strDrink : String,
    var strCategory: String,
    var strDrinkThumb: String,
    var strInstructions: String
)

fun List<DatabaseCocktail>.asDomainModel(): List<Cocktail> {
    return map{
        Cocktail(
            strDrink =it.strDrink,
            strCategory = it.strCategory,
            strDrinkThumb = it.strDrinkThumb,
            strInstructions = it.strInstructions
        )
    }
}