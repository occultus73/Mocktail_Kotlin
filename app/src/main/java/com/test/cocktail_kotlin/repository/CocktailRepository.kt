package com.test.cocktail_kotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.cocktail_kotlin.database.CocktailDatabase
import com.test.cocktail_kotlin.database.asDomainModel
import com.test.cocktail_kotlin.domain.Cocktail
import com.test.cocktail_kotlin.network.CocktailByteNetwork
import com.test.cocktail_kotlin.network.Constants
import com.test.cocktail_kotlin.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CocktailRepository(private val database: CocktailDatabase) {

    suspend fun refreshCocktail() {
        withContext(Dispatchers.IO){
            Timber.d("refresh cocktail is called")
            val cocktailList = CocktailByteNetwork.cocktailBytes.getCocktailList(Constants.API_KEY).await()
            database.cocktailDao.insertAll(cocktailList.asDatabaseModel())
        }
    }

    val results:LiveData<List<Cocktail>> = Transformations.map(database.cocktailDao.getCocktails()) {
        it.asDomainModel()
    }
}