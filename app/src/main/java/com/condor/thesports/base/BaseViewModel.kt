package com.condor.thesports.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {

    fun clearViewModel() {
        onCleared()
    }
}