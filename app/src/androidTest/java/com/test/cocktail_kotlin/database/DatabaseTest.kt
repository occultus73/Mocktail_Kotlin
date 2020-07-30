package com.test.cocktail_kotlin.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {
    // system under test
    private lateinit var cocktailDatabase: CocktailDatabase

    val dao: CocktailDao
        get() = cocktailDatabase.cocktailDao

    @Before
    fun init() {
        cocktailDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CocktailDatabase::class.java
        ).build()
    }

    @After
    fun finish() {
        cocktailDatabase.close()
    }

}