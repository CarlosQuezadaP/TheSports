package com.condor.usecases.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.condor.core.ResultWrapper
import com.condor.data.handler.TeamRepositoryHandler
import com.condor.domain.models.TeamDomain
import com.condor.usecases.builder.BuilderTeam
import com.condor.usecases.GetAllTeamsUseCase
import com.condor.usecases.utils.MainCoroutineScopeRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class GetAllTeamsUseCaseTest {

    lateinit var teamBuilder: BuilderTeam

    private val repositoryHandlerFullData: TeamRepositoryHandler = mockk()


    lateinit var teamSuccessDataFlow: Flow<ResultWrapper<List<TeamDomain>>>

    private val teamRepositoryHandler: TeamRepositoryHandler = mockk()

    private val emptyDataFlow = flowOf(ResultWrapper.Success(emptyList<TeamDomain>()))

    private val parameterLeague = "Spanish La Liga"

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Before
    fun setup() {
        teamBuilder = BuilderTeam()

        teamSuccessDataFlow = flowOf(
            ResultWrapper.Success(
                teamBuilder.buildAsList()
            )
        )

        every {
            repositoryHandlerFullData.getAll(parameterLeague)
        } returns teamSuccessDataFlow

        every {
            teamRepositoryHandler.getAll("")
        } returns emptyDataFlow
    }

    @Test
    fun getAllTeams_responseSuccess() {
        coroutineScope.runBlockingTest {
            //Arrange
            val getAllTeamsUseCase = GetAllTeamsUseCase(repositoryHandlerFullData)
            val expectedValue = teamBuilder.buildAsList()


            //Act
            val response: Flow<ResultWrapper<List<TeamDomain>>> =
                getAllTeamsUseCase.invoke(parameterLeague)

            //Assert
            response.collect { value: ResultWrapper<List<TeamDomain>> ->
                when (value) {
                    is ResultWrapper.Loading -> {
                        Assert.assertEquals(ResultWrapper.Loading, value)
                    }
                    is ResultWrapper.Success -> {
                        Assert.assertEquals(expectedValue, value.data)
                    }
                    is ResultWrapper.Error -> {
                        Assert.assertEquals(ResultWrapper.Error(""), value)
                    }
                }
            }
        }
    }


    @Test
    fun getAllTeam_validateEmptyData() {
        coroutineScope.runBlockingTest {
            //Arrange
            val getAllTeamsUseCase = GetAllTeamsUseCase(teamRepositoryHandler)
            val expectedValue = listOf<TeamDomain>()
            val parameter = ""

            //Act
            val response: Flow<ResultWrapper<List<TeamDomain>>> =
                getAllTeamsUseCase.invoke(parameter)

            //Assert
            response.collect { value: ResultWrapper<List<TeamDomain>> ->
                when (value) {
                    is ResultWrapper.Loading -> {
                        Assert.assertEquals(ResultWrapper.Loading, value)
                    }
                    is ResultWrapper.Success -> {
                        Assert.assertEquals(expectedValue, value.data)
                    }
                    is ResultWrapper.Error -> {
                        Assert.assertEquals(ResultWrapper.Error(""), value)
                    }
                }
            }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}