package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.usecases.IRetrieveAllTeams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamsListViewModel(private val retrieveAllTeams: IRetrieveAllTeams) : ViewModel() {

    private var _lvTeams: MutableLiveData<ResultWrapper<List<TeamDomain>>> = MutableLiveData()
    var lvTeams: LiveData<ResultWrapper<List<TeamDomain>>> = _lvTeams

    fun getTeams(leagueParameter: String) {
        viewModelScope.launch {
            retrieveAllTeams.invoke(leagueParameter).collect {
                _lvTeams.value = it
            }
        }
    }
}