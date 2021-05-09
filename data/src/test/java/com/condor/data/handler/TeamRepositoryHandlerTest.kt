package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.handler.fake.FakeLocalSource
import com.condor.data.handler.fake.FakeRemoteSource
import com.condor.data.handler.fake.fakeTeams
import com.condor.usecases.GetAllTeamsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TeamRepositoryHandlerTest {

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun getILocalRepository() {
        coroutineScopeRule.dispatcher.runBlockingTest {
            val teamRepositoryHandler =
                TeamRepositoryHandler(FakeLocalSource(), FakeRemoteSource(fakeTeams))

            val getAllTeamsUseCase = GetAllTeamsUseCase(teamRepositoryHandler)

            val result = getAllTeamsUseCase.invoke("The spanish league")

            result.collect {
                when (it) {
                    is ResultWrapper.Success -> {
                        Assert.assertEquals(it.data, fakeTeams)
                    }
                }
            }

        }
    }
}