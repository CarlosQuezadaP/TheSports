package com.condor.thesports.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.activity.LeaguesActivity
import com.condor.thesports.adapter.TeamsAdapter
import com.condor.thesports.databinding.FragmentListTeamsBinding
import com.condor.thesports.fragments.base.BaseFragment
import com.condor.thesports.handlers.OnClick
import com.condor.thesports.helpers.setExitToFullScreenTransition
import com.condor.thesports.helpers.setReturnFromFullScreenTransition
import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.android.ext.android.inject

private const val REQUEST_CODE = 222

private const val DATA_NAME = "LEAGUE_NAME"

class ListTeams : BaseFragment<TeamsListViewModel, FragmentListTeamsBinding>(),
    OnClick<TeamDomain> {

    override val viewModel: TeamsListViewModel by inject()

    override val layoutId = R.layout.fragment_list_teams

    private lateinit var teamsAdapter: TeamsAdapter

    private lateinit var leagueName: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueName = getString(R.string.league_default)

        setupRecyclerView()

        getTeamsLeague(leagueName)

        observeTeams()

        databinding.llActionbarSelectLeague.setOnClickListener {
            startActivityForResult(
                Intent(requireContext(), LeaguesActivity::class.java),
                REQUEST_CODE
            )
        }

        setExitToFullScreenTransition()
        setReturnFromFullScreenTransition()
    }

    private fun showLoading(value: Boolean) {
        val one = if (value) View.VISIBLE else View.GONE
        val two = if (value) View.GONE else View.VISIBLE

        databinding.apply {
            includedProgress.progressBar.visibility = one
            llActionbarSelectLeague.visibility = two
            recyclerViewTeams.visibility = two
        }
    }

    private fun observeTeams() {
        viewModel.lvTeams.observe(
            viewLifecycleOwner
        ) { resultWrapper: ResultWrapper<List<TeamDomain>> ->
            when (resultWrapper) {
                is ResultWrapper.Loading -> {
                    showLoading(true)
                }
                is ResultWrapper.Success -> {
                    databinding.textViewLeagueTitle.text = leagueName
                    teamsAdapter.submitList(resultWrapper.data)
                    showLoading(false)
                }
                is ResultWrapper.Error -> {
                    showLoading(false)
                    toastUtils.showLong(resultWrapper.message)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        teamsAdapter = TeamsAdapter(this)
        databinding.recyclerViewTeams.run {
            adapter = teamsAdapter
            layoutManager =
                GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                leagueName = data?.getStringExtra(DATA_NAME) ?: ""
                getTeamsLeague(leagueName)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                toastUtils.showLong(getString(R.string.canceled_action))
            }
        }
    }

    private fun getTeamsLeague(leagueName: String) {
        if (leagueName.isEmpty()) {
            toastUtils.showLong(getString(R.string.no_league_selected))
        } else {
            viewModel.getTeams(leagueName)
        }
    }

    override fun OnClick(data: TeamDomain) {
        val action =
            ListTeamsDirections.actionListTeamsToDetailTeamFragment(data.idTeam, data.strTeam)
        findNavController().navigate(action)
    }


}