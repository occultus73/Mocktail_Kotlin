package com.test.cocktail_kotlin.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    //https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita

    @GET("api/json/v1/1/search.php")
    fun getCocktailList(@Query("s") apiKey: String?) : Deferred<NetworkCocktailContainer>
}