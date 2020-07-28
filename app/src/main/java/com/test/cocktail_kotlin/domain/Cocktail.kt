package com.test.cocktail_kotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Cocktail (
    var strDrink : String,
    var strCategory: String,
    var strDrinkThumb: String,
    var strInstructions: String
):Parcelable{

}

