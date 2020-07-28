package com.test.cocktail_kotlin.network

import com.squareup.moshi.JsonClass
import com.test.cocktail_kotlin.database.DatabaseCocktail


/**
 * Note: Make sure to mention json object name here, as val(for val drinks)
 */
@JsonClass(generateAdapter = true)
data class NetworkCocktailContainer(val drinks: List<NetworkCocktail>)

@JsonClass(generateAdapter = true)
data class NetworkCocktail(
    var strDrink : String,
    var strCategory: String,
    var strDrinkThumb: String,
    var strInstructions: String
)

fun NetworkCocktailContainer.asDatabaseModel() : List<DatabaseCocktail> {
    return drinks.map {
            DatabaseCocktail(
                strDrink = it.strDrink,
                strCategory = it.strCategory,
                strDrinkThumb = it.strDrinkThumb,
                strInstructions = it.strInstructions
            )
    }
}