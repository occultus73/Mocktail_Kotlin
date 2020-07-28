package com.test.cocktail_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.test.cocktail_kotlin.databinding.FragmentSecondBinding
import com.test.cocktail_kotlin.databinding.FragmentSecondBindingImpl
import com.test.cocktail_kotlin.viewmodel.CocktailDetailViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_second, container, false)
        val application = requireNotNull(activity).application
        val binding = FragmentSecondBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val cocktailProperty = SecondFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory =
            CocktailDetailViewModel.CocktailDetailViewModelFactory(cocktailProperty, application)
        binding.viewModel= ViewModelProviders.of(this,viewModelFactory).get(CocktailDetailViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }
}