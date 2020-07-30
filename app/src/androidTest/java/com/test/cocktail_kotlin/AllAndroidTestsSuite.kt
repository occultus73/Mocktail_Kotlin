package com.test.cocktail_kotlin

import com.test.cocktail_kotlin.database.DaoTest
import com.test.cocktail_kotlin.ui.FirstFragmentTest
import com.test.cocktail_kotlin.ui.MainActivityTest
import com.test.cocktail_kotlin.ui.NavigationTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    NavigationTest::class,
    FirstFragmentTest::class,
    DaoTest::class
)
class AllAndroidTestsSuite