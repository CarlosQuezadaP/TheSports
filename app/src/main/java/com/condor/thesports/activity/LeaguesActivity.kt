package com.condor.thesports.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.adapter.LeagueListAdapter
import com.condor.thesports.base.BaseActivity
import com.condor.thesports.databinding.ActivityLeaguesBinding
import com.condor.thesports.handlers.ISelectLeague
import com.condor.thesports.utils.ToastUtils
import com.condor.thesports.viewmodels.LeagueViewModel
import org.koin.android.ext.android.inject

private const val DATA_NAME = "LEAGUE_NAME"

class LeaguesActivity : BaseActivity(), ISelectLeague {

    private var leaguesBinding: ActivityLeaguesBinding? = null
    private lateinit var listActivityAdapter: LeagueListAdapter
    private val leagueViewModel: LeagueViewModel by inject()
    private val toastUtils: ToastUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaguesBinding = ActivityLeaguesBinding.inflate(layoutInflater)
        setContentView(leaguesBinding?.root)

        setAdapter()

        observeLeagues()

        leaguesBinding?.imageButtonClose?.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        listActivityAdapter = LeagueListAdapter(this)
        leaguesBinding?.recyclerViewList?.adapter = listActivityAdapter
    }

    private fun observeLeagues() {
        leagueViewModel.apply {
            getAllLeagues()
            lvLeague.observe(
                this@LeaguesActivity,
                { resultWrapper: ResultWrapper<List<LeagueDomain>> ->
                    when (resultWrapper) {
                        is ResultWrapper.Loading -> {
                            //Todo mostrar loading
                        }
                        is ResultWrapper.Success -> {
                            listActivityAdapter.submitList(resultWrapper.data)
                        }
                        is ResultWrapper.Error -> {
                            toastUtils.show(resultWrapper.message)
                        }
                    }
                })
        }
    }

    override fun selectLeague(leagueDomain: LeagueDomain) {
        val intent = Intent().apply {
            putExtra(DATA_NAME, leagueDomain.strLeague)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onDestroy() {
        leagueViewModel.clearViewModel()
        leaguesBinding = null
        super.onDestroy()
    }

}