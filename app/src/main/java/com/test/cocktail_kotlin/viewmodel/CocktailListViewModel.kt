package com.test.cocktail_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.test.cocktail_kotlin.database.getDatabase
import com.test.cocktail_kotlin.domain.Cocktail
import com.test.cocktail_kotlin.repository.CocktailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class CocktailListViewModel(application: Application) : AndroidViewModel(application) {

    private val cocktailRepository = CocktailRepository(getDatabase(application))

    val cocktaillist = cocktailRepository.results

    //This is the job for all coroutines started by this ViewModel

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //Event triggered for network error.

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() =_eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    //init{} is called immediately when this ViewModel is created.
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                cocktailRepository.refreshCocktail()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }catch (networkError : IOException) {
                if(cocktaillist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    //reset the network error flag
        fun onNetworkErrorShown() {
            _isNetworkErrorShown.value = true
    }

    //cancel all coroutines when the viewmodel is cleared
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //Factory for constructing CocktailListViewModel with parameter
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CocktailListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CocktailListViewModel(app) as T
            }
            throw IllegalArgumentException("unable to construct viewmodel")
        }

    }


    //for display data in second fragment
    // LiveData to handle navigation to the selected Cocktail
    private val _navigateToSelectedProperty = MutableLiveData<Cocktail>()
    val navigateToSelectedProperty: LiveData<Cocktail>
        get() = _navigateToSelectedProperty

    /**
     * When the cocktail is clicked, set the [_navigateToSelectedProperty] [MutableLiveData]
     * @param cocktailProperty The [Cocktail] that was clicked on.
     */

    fun displayPropertyDetails(cocktailProperty: Cocktail) {
        _navigateToSelectedProperty.value = cocktailProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}