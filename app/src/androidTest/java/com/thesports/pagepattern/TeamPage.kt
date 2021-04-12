package com.thesports.pagepattern

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.condor.thesports.R
import com.condor.thesports.adapter.TeamViewHolder

class TeamPage : Page() {

    @IdRes
    val teamFundationTextView: Int = R.id.tvTeamFoundationYear

    fun clickToRecyclerItemAction(position: Int, idRecyclerView: Int): Page {
        onView(withId(idRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TeamViewHolder>(
                    position,
                    click()
                )
            )
        return this
    }

    fun assertText(text: String) {
        onView(withId(teamFundationTextView)).check(matches(withText(text)))
    }

    fun clickview(idView: Int) {
        onView(withId(idView))
            .perform(click())
    }

}