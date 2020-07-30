package com.test.cocktail_kotlin

import LiveDataTestUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.cocktail_kotlin.database.CocktailDatabase
import com.test.cocktail_kotlin.database.DatabaseCocktail
import com.test.cocktail_kotlin.domain.Cocktail
import com.test.cocktail_kotlin.repository.CocktailRepository
import com.test.cocktail_kotlin.test_utils.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class RepositoryTest {

    // system under test
    private lateinit var repository: CocktailRepository

    @MockK
    lateinit var database: CocktailDatabase

    private val cocktailInsert = DatabaseCocktail(
        "Margarita",
        "Ordinary Drink",
        "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass."
    )

    @BeforeEach
    fun initEach(){
        MockKAnnotations.init(this)
    }

    @Test
    fun test_results_transformation_map() {
        // Arrange
        val liveDataTestUtil: LiveDataTestUtil<List<Cocktail>> = LiveDataTestUtil()
        val getCocktailsResponse: LiveData<List<DatabaseCocktail>> = MutableLiveData<List<DatabaseCocktail>>().apply{value = listOf(cocktailInsert)}
        every { database.cocktailDao.getCocktails() } returns getCocktailsResponse
        repository = CocktailRepository(database)

        // Act
        val liveTransformedResults: LiveData<List<Cocktail>> = repository.results
        val transformedResults: List<Cocktail> = liveDataTestUtil.getValue(liveTransformedResults)

        // Assert
        verify(exactly = 1){
            database.cocktailDao.getCocktails()
        }
        assertNotNull(transformedResults)
        assertEquals(cocktailInsert.strDrink, transformedResults[0].strDrink)
        assertEquals(cocktailInsert.strCategory, transformedResults[0].strCategory)
        assertEquals(cocktailInsert.strDrinkThumb, transformedResults[0].strDrinkThumb)
        assertEquals(cocktailInsert.strInstructions, transformedResults[0].strInstructions)
    }
}