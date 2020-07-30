package com.test.cocktail_kotlin.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.test.cocktail_kotlin.MainActivity
import com.test.cocktail_kotlin.R
import com.test.cocktail_kotlin.adapter.CocktailViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigation() {

        // Navigate to Second Fragment
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<CocktailViewHolder>(
                0,
                click()
            )
        )

        // Verify Second Fragment Showing
        onView(withId(R.id.Second_Fragment_Layout)).check(matches(isDisplayed()))

        // Navigate back to First Fragment
        pressBack()

        // Verify First Fragment is Showing
        onView(withId(R.id.First_Fragment_Layout)).check(matches(isDisplayed()))
    }
}
