package com.test.cocktail_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.cocktail_kotlin.R
import com.test.cocktail_kotlin.databinding.RowCocktailBinding
import com.test.cocktail_kotlin.domain.Cocktail

class CocktailAdapter(val callback: CocktailClick) : RecyclerView.Adapter<CocktailViewHolder>() {

// cocktail list that our adaptor will show
    var results: List<Cocktail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val withDataBinding: RowCocktailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CocktailViewHolder.LAYOUT,
            parent,
            false)
        return CocktailViewHolder(withDataBinding)

    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.results = results[position]

            //To handle onclick
            it.resultsCallback = callback
            }
    }


}

//ViewHolder for cocktail list, all work is done by data binding
class CocktailViewHolder(val viewDataBinding: RowCocktailBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_cocktail
    }
}

/**
 * Click listener for cocktail. By giving the block a name it helps a reader understand what it does.
 *
 */
class CocktailClick(val block: (Cocktail) -> Unit) {
    /**
     * Called when a cocktail is clicked
     *
     * @param cocktail the cocktail that was clicked
     */
    fun onClick(cocktail: Cocktail) = block(cocktail)
}
