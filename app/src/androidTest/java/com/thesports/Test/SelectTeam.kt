package com.thesports.Test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.condor.thesports.R
import com.condor.thesports.activity.SplashActivity
import com.thesports.pagepattern.Page
import com.thesports.pagepattern.PageUtils
import com.thesports.pagepattern.TeamPage
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@InternalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SelectTeam {

    private lateinit var pageUtils: PageUtils

    @get:Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun initPageUtils() {
        pageUtils = PageUtils()
    }

    fun startActivity() {
        activityRule.scenario
    }


    @Test
    fun SelectTeamAndCompareYear() {
        startActivity()
        pageUtils.sleep(8)
        Page.on<TeamPage>().clickToRecyclerItemAction(0, R.id.recyclerView_teams)
        Page.on<TeamPage>().assertText("1921")
    }


}