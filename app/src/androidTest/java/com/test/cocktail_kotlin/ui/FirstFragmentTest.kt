package com.test.cocktail_kotlin.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.test.cocktail_kotlin.MainActivity
import com.test.cocktail_kotlin.R
import com.test.cocktail_kotlin.android_test_utils.RecyclerViewTestUtils.withRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FirstFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isCocktailDataVisible() {

        val strCategory: String = "Ordinary Drink"
        val strDrink: String = "Margarita"

//        CANNOT FIND USE FOR MOCKK WITHOUT INJECTION.
//        val cocktailsDataSource = mockk<>()
//        every {
//        } returns

        //CANNOT MAKE THIS WORK IN THIS APPLICATION
        //val scenario = launchFragmentInContainer<FirstFragment>()


        // utilises dannyroa's recyclerview custom matcher
        // https://github.com/dannyroa/espresso-samples/tree/master/RecyclerView/app/src/androidTest/java/com/dannyroa/espresso_samples/recyclerview
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0,
            R.id.title
        )).check(matches(withText(strDrink)))
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0,
            R.id.description
        )).check(matches(withText(strCategory)))
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0,
            R.id.video_thumbnail
        )).check(matches(isDisplayed()))

    }
}