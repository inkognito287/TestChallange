package com.example.visual.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.visual.R
import org.junit.Before
import org.junit.Test

internal class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }
    @Test
    fun `testLaunchSecondActivity`() {
        onView(withId(R.id.`@+id/button_create_order_binding`)).perform(click())
        onView(withId(R.id.rcView)).check(matches(isDisplayed()))
    }
    @Test
    fun `testLaunchThirdActivityProgressBar`(){
        onView(withId(R.id.Documentation)).perform(click())
        onView(withId(R.id.progressBar)).check(matches(isEnabled()))
    }
    @Test
    fun `testLaunchThirdActivityViewPager`() {
        onView(withId(R.id.Documentation)).perform(click())
        onView(withId(R.id.viewpager2)).check(matches(isEnabled()))
    }
}