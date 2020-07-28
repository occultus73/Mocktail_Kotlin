package com.test.cocktail_kotlin.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CocktailDao {
    @Query("select * from databasecocktail")
    fun getCocktails():LiveData<List<DatabaseCocktail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cocktails :List<DatabaseCocktail>)
}

@Database(entities = [DatabaseCocktail::class],version = 2,exportSchema = false)
abstract class CocktailDatabase: RoomDatabase() {
    abstract val cocktailDao: CocktailDao
}

private lateinit var INSTANCE: CocktailDatabase

fun getDatabase(context : Context): CocktailDatabase {
    synchronized(CocktailDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            CocktailDatabase::class.java,"results")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}