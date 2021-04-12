package com.condor.thesports.handlers

import com.condor.domain.models.LeagueDomain

interface ISelectLeague {
    fun selectLeague(leagueDomain: LeagueDomain)

}