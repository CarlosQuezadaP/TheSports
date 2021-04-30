package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.thesports.base.BaseViewModel
import com.condor.usecases.IGetAllTeamsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamsListViewModel(private val getAllTeamsUseCase: IGetAllTeamsUseCase) : BaseViewModel() {

    private var _lvTeams: MutableLiveData<ResultWrapper<List<TeamDomain>>> = MutableLiveData()
    var lvTeams: LiveData<ResultWrapper<List<TeamDomain>>> = _lvTeams

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun getTeams(leagueParameter: String) {
        _loading.value = true
        viewModelScope.launch {
            getAllTeamsUseCase.invoke(leagueParameter).collect {
                _lvTeams.value = it
                _loading.value = false

            }
        }
    }
}