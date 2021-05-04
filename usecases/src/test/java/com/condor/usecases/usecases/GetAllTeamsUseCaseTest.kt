package com.condor.usecases.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.usecases.GetAllTeamsUseCase
import com.condor.usecases.builder.BuilderTeam
import com.condor.usecases.repository.ITeamRepositoryHandler
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

@ExperimentalCoroutinesApi
class GetAllTeamsUseCaseTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val repositoryHandlerFullData: ITeamRepositoryHandler = mockk()

    private val teamRepositoryHandler: ITeamRepositoryHandler = mockk()

    lateinit var teamBuilder: BuilderTeam

    lateinit var listTeam: List<TeamDomain>

    val emptyList: List<TeamDomain> = listOf()

    lateinit var teamSuccessDataFlow: Flow<ResultWrapper<List<TeamDomain>>>

    private val emptyDataFlow = flowOf(ResultWrapper.Success(emptyList))

    private val parameterLeague = "Spanish La Liga"

    @Before
    fun setup() {
        teamBuilder = BuilderTeam()

        listTeam = teamBuilder.buildAsList()

        teamSuccessDataFlow = flowOf(
            ResultWrapper.Success(
                listTeam
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
    fun responseSuccess() = coroutineScope.dispatcher.runBlockingTest {
        //Arrange
        val getAllTeamsUseCase = GetAllTeamsUseCase(repositoryHandlerFullData)
        val expectedValue = listTeam

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


    @Test
    fun validateEmptyData() =
        coroutineScope.dispatcher.runBlockingTest {
            //Arrange
            val getAllTeamsUseCase = GetAllTeamsUseCase(teamRepositoryHandler)
            val expectedValue = emptyList
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

    @After
    fun tearDown() {
        unmockkAll()
    }
}