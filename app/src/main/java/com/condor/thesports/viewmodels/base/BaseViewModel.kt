package com.condor.thesports.viewmodels.base

import androidx.lifecycle.ViewModel

//Todo agregar un dispatcher en el constructor
abstract class BaseViewModel : ViewModel() {

    fun clearViewModel() {
        onCleared()
    }
}