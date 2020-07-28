package com.test.cocktail_kotlin

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    NavigationTest::class,
    FirstFragmentTest::class
)
class AllTestsSuite