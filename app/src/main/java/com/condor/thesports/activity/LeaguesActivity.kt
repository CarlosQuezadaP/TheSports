package com.condor.thesports.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.activity.base.BaseActivityVM
import com.condor.thesports.adapter.LeagueListAdapter
import com.condor.thesports.databinding.ActivityLeaguesBinding
import com.condor.thesports.handlers.OnClick
import com.condor.thesports.viewmodels.LeagueViewModel
import org.koin.android.ext.android.inject

private const val DATA_NAME = "LEAGUE_NAME"

class LeaguesActivity : BaseActivityVM<LeagueViewModel, ActivityLeaguesBinding>(),
    OnClick<LeagueDomain> {

    private lateinit var listActivityAdapter: LeagueListAdapter
    override val viewModel: LeagueViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = ActivityLeaguesBinding.inflate(layoutInflater)
        setContentView(databinding?.root)

        databinding?.apply {
            viewModel = this@LeaguesActivity.viewModel
            imageButtonClose.setOnClickListener {
                finish()
            }
        }
        setAdapter()
        observeLeagues()

    }

    private fun setAdapter() {
        listActivityAdapter = LeagueListAdapter(this)
        databinding?.recyclerViewList?.adapter = listActivityAdapter
    }

    private fun observeLeagues() {
        viewModel.apply {
            getAllLeagues()
            lvLeague.observe(
                this@LeaguesActivity
            ) { resultWrapper: ResultWrapper<List<LeagueDomain>> ->
                when (resultWrapper) {
                    is ResultWrapper.Loading -> {
                        showLoading(true)
                    }
                    is ResultWrapper.Success -> {
                        listActivityAdapter.submitList(resultWrapper.data)
                        showLoading(false)
                    }
                    is ResultWrapper.Error -> {
                        showLoading(false)
                        toastUtils.showLong(resultWrapper.message)
                    }
                }
            }
        }
    }

    private fun showLoading(value: Boolean) {

        val one = if (value) View.VISIBLE else View.GONE
        val two = if (value) View.GONE else View.VISIBLE

        databinding?.apply {
            includedProgress.progressBar.visibility = one
            llContent.visibility = two
            imageButtonClose.visibility = two
        }
    }

    override fun OnClick(data: LeagueDomain) {
        val intent = Intent().apply {
            putExtra(DATA_NAME, data.strLeague)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

}