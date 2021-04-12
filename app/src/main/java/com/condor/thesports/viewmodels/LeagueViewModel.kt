package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.usecases.IGetAllLeaguesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LeagueViewModel(private val iGetAllLeaguesUseCase: IGetAllLeaguesUseCase) : ViewModel() {

    private var _lvLeagues: MutableLiveData<ResultWrapper<List<LeagueDomain>>> = MutableLiveData()
    var lvLeague: LiveData<ResultWrapper<List<LeagueDomain>>> = _lvLeagues

    fun getAllLeagues() {
        viewModelScope.launch {
            iGetAllLeaguesUseCase.invoke().collect {
                _lvLeagues.value = it
            }
        }
    }
}