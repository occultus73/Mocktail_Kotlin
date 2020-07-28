package com.test.cocktail_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.cocktail_kotlin.adapter.CocktailAdapter
import com.test.cocktail_kotlin.adapter.CocktailClick
import com.test.cocktail_kotlin.databinding.FragmentFirstBindingImpl
import com.test.cocktail_kotlin.domain.Cocktail
import com.test.cocktail_kotlin.viewmodel.CocktailListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
private var viewModelAdapter: CocktailAdapter? = null

    /**LAZY
     *One way to delay creation of the viewmodel until an appropriate lifecycle method is to use Lazy
     *This requires that viewmodel not be referenced before onActivityCreated, which we do in this fragment
     */

    private val viewModel:CocktailListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, CocktailListViewModel.Factory(activity.application))
            .get(CocktailListViewModel::class.java)
    }

    /**Method for displaying a toast error message for network errors.
     *
     */

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity,"Network Error",Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
        val binding: FragmentFirstBindingImpl = DataBindingUtil.inflate(inflater,R.layout.fragment_first, container, false)

        binding.setLifecycleOwner (viewLifecycleOwner)
        binding.viewmodel = viewModel
       // viewModelAdapter = CocktailAdapter()

        //Sets the adapter of the  RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        viewModelAdapter = CocktailAdapter(CocktailClick {
           // val packageManager = context?.packageManager ?: return@CocktailClick
            viewModel.displayPropertyDetails(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }


        //Observer for the network error
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
            if(isNetworkError) onNetworkError()
        })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(it))
            // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cocktaillist.observe(viewLifecycleOwner, Observer<List<Cocktail>> {cocktail ->
        cocktail?.apply {
            viewModelAdapter?.results =cocktail
        }
        })


    }
}