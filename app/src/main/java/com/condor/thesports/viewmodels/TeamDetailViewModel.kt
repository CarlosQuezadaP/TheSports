package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import com.condor.thesports.viewmodels.base.BaseViewModel
import com.condor.usecases.IGetAllEventsUseCase
import com.condor.usecases.IGetTeamUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val iGetTeamUseCase: IGetTeamUseCase,
    private val iGetAllEventsUseCase: IGetAllEventsUseCase
) : BaseViewModel() {

    private var _teamLiveData: MutableLiveData<ResultWrapper<TeamDomain>> = MutableLiveData()
    var teamLiveData: LiveData<ResultWrapper<TeamDomain>> = _teamLiveData

    private var _eventsLiveData: MutableLiveData<ResultWrapper<List<EventDomain>>> =
        MutableLiveData()
    var eventsLiveData: LiveData<ResultWrapper<List<EventDomain>>> = _eventsLiveData

    fun getTeam(idTeam: String) {
        viewModelScope.launch {
            iGetTeamUseCase.invoke(idTeam)
                .onStart {
                    emit(ResultWrapper.Loading)
                }
                .catch { e ->
                    emit(ResultWrapper.Error("Network error: ${e.message}"))
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _teamLiveData.value = it
                }
        }
    }

    fun getEventsByTeamId(teamId: String) {
        viewModelScope.launch {
            iGetAllEventsUseCase.invoke(teamId)
                .onStart {
                    emit(ResultWrapper.Loading)
                }.catch { e ->
                    emit(ResultWrapper.Error("Network error: ${e.message}"))
                }.flowOn(Dispatchers.IO)
                .collect {
                    _eventsLiveData.value = it
                }
        }
    }

}