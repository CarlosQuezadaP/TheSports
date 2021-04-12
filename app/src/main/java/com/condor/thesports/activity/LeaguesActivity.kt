package com.condor.thesports.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.adapter.LeagueListAdapter
import com.condor.thesports.databinding.ActivityLeaguesBinding
import com.condor.thesports.handlers.ISelectLeague
import com.condor.thesports.viewmodels.LeagueViewModel
import org.koin.android.ext.android.inject

private const val DATA_NAME = "LEAGUE_NAME"

class LeaguesActivity : AppCompatActivity(), ISelectLeague {

    private lateinit var leaguesBinding: ActivityLeaguesBinding
    private lateinit var listActivityAdapter: LeagueListAdapter
    private val leagueViewModel: LeagueViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaguesBinding = ActivityLeaguesBinding.inflate(layoutInflater)
        setContentView(leaguesBinding.root)

        setAdapter()
        leagueViewModel.getAllLeagues()
        observeLeagues()

        leaguesBinding.imageButtonClose.setOnClickListener {
            finish()
        }
    }


    private fun setAdapter() {
        listActivityAdapter = LeagueListAdapter(this)
        leaguesBinding.recyclerViewList.adapter = listActivityAdapter
    }

    private fun observeLeagues() {
        leagueViewModel.lvLeague.observe(
            this@LeaguesActivity,
            { resultWrapper: ResultWrapper<List<LeagueDomain>> ->
                when (resultWrapper) {
                    is ResultWrapper.Loading -> {

                    }
                    is ResultWrapper.Success -> {
                        listActivityAdapter.submitList(resultWrapper.data)
                    }
                    is ResultWrapper.Error -> {
                        Toast.makeText(this, resultWrapper.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    override fun selectLeague(leagueDomain: LeagueDomain) {
        val _leagueDomain = leagueDomain
        val intent = Intent().apply {
            putExtra(DATA_NAME, _leagueDomain.strLeague)
        }
        setResult(RESULT_OK, intent)
        finish()
    }


}