package com.test.cocktail_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.test.cocktail_kotlin.domain.Cocktail

class CocktailDetailViewModel(cocktailProperty: Cocktail, app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData for the selected cocktail
    private val _selectedProperty = MutableLiveData<Cocktail>()

    // The external LiveData for the SelectedCocktail
    val selectedProperty: LiveData<Cocktail>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = cocktailProperty
    }


    /**
     * Simple ViewModel factory that provides the cocktailProperty and context to the ViewModel.
     */

    class CocktailDetailViewModelFactory (
        private val cocktailProperty: Cocktail , private val application:Application) :
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CocktailDetailViewModel::class.java)) {
                return CocktailDetailViewModel(cocktailProperty, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }
}
