package com.test.cocktail_kotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testActivity_inView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisibility_FloatingActionButton() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisibility_Toolbar() { // Alternative visibility check:
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

}