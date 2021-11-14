package com.wasilyk.app.testing2

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<ListFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(
            bundleOf(ListFragment.HEADER_TITLE to "header from test")
        )
    }

    @Test
    fun fragment_IsNotNull() {
        scenario.onFragment {
            assertNotNull(it)
        }
    }

    @Test
    fun fragment_TestBundle() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        val assertion = ViewAssertions.matches(withText("header from test"))
        onView(withId(R.id.header_title)).check(assertion)
    }

    @Test
    fun fragmentRecyclerView_TestScrollTo() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.rv))
            .perform(
                RecyclerViewActions.scrollTo<ListAdapter.ViewHolder>(
                    hasDescendant(withText("Наташа"))
                )
            )
    }

    @Test
    fun fragmentRecyclerView_TestPerformClickAtPosition() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
    }
}