package com.condor.thesports.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.thesports.adapter.TeamsAdapter
import com.condor.thesports.databinding.ActivityMainBinding
import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val teamsListViewModel: TeamsListViewModel by inject()
    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupRecyclerView()

        teamsListViewModel.getTeams("Spanish La Liga")

        observeTeams()
    }

    private fun setupDataBinding() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        mainBinding.apply {
            lifecycleOwner = this@MainActivity
        }

        setContentView(mainBinding.root)
    }

    private fun observeTeams() {
        teamsListViewModel.lvTeams.observe(this, { resultWrapper: ResultWrapper<List<TeamDomain>> ->
            when (resultWrapper) {
                is ResultWrapper.Loading -> {
                }
                is ResultWrapper.Success -> {
                    val data = resultWrapper.data
                    teamsAdapter.submitList(data)
                }
                is ResultWrapper.Error -> {
                }
            }
        })
    }


    private fun setupRecyclerView() {
        teamsAdapter = TeamsAdapter()
        mainBinding.recyclerViewTeams.run {
            adapter = teamsAdapter
            layoutManager =
                GridLayoutManager(this@MainActivity, 3, LinearLayoutManager.VERTICAL, false)
        }
    }


}