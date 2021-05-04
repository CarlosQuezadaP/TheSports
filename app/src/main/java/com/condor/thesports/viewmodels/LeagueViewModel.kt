package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.base.BaseViewModel
import com.condor.usecases.IGetAllLeaguesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LeagueViewModel(private val iGetAllLeaguesUseCase: IGetAllLeaguesUseCase) : BaseViewModel() {

    private var _lvLeagues: MutableLiveData<ResultWrapper<List<LeagueDomain>>> = MutableLiveData()

    var lvLeague: LiveData<ResultWrapper<List<LeagueDomain>>> = _lvLeagues

    fun getAllLeagues() {
        viewModelScope.launch {
            iGetAllLeaguesUseCase.invoke()
                .onStart {
                    emit(ResultWrapper.Loading)
                }.catch {
                    emit(ResultWrapper.Error("League error"))
                }.flowOn(Dispatchers.IO)
                .collect {
                    _lvLeagues.value = it
                }
        }
    }

}