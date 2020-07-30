package com.test.cocktail_kotlin.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test



class DaoTest : DatabaseTest() {

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_insert_and_query() {
        //Arrange
        val cocktailInsert = DatabaseCocktail(
            "Margarita",
            "Ordinary Drink",
            "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
            "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass."
        )
        val liveDataTestUtil: LiveDataTestUtil<List<DatabaseCocktail>> = LiveDataTestUtil()

        //Action
        dao.insertAll(listOf(cocktailInsert))
        val cocktailQuery = liveDataTestUtil.getValue(dao.getCocktails())

        //Assert
        assertNotNull(cocktailQuery)
        assertEquals(cocktailInsert.strDrink, cocktailQuery[0].strDrink)
        assertEquals(cocktailInsert.strCategory, cocktailQuery[0].strCategory)
        assertEquals(cocktailInsert.strDrinkThumb, cocktailQuery[0].strDrinkThumb)
        assertEquals(cocktailInsert.strInstructions, cocktailQuery[0].strInstructions)
    }
}