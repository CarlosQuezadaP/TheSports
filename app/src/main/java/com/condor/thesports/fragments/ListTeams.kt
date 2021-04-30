package com.condor.thesports.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.activity.LeaguesActivity
import com.condor.thesports.adapter.TeamsAdapter
import com.condor.thesports.base.BaseFragment
import com.condor.thesports.databinding.FragmentListTeamsBinding
import com.condor.thesports.handlers.OnTeamClick
import com.condor.thesports.helpers.setExitToFullScreenTransition
import com.condor.thesports.helpers.setReturnFromFullScreenTransition
import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.android.ext.android.inject

private const val REQUEST_CODE = 222

private const val DATA_NAME = "LEAGUE_NAME"

class ListTeams : BaseFragment(), OnTeamClick {

    private lateinit var listTeamBinding: FragmentListTeamsBinding

    private val teamsListViewModel: TeamsListViewModel by inject()

    private lateinit var teamsAdapter: TeamsAdapter

    private lateinit var leagueName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        listTeamBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_teams, container, false
        )
        leagueName = getString(R.string.league_default)

        setupRecyclerView()

        getTeamsLeague(leagueName)

        observeTeams()

        listTeamBinding.llActionbarSelectLeague.setOnClickListener {
            startActivityForResult(
                Intent(requireContext(), LeaguesActivity::class.java),
                REQUEST_CODE
            )
        }

        return listTeamBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setExitToFullScreenTransition()
        setReturnFromFullScreenTransition()
    }


    private fun observeTeams() {
        teamsListViewModel.lvTeams.observe(
            viewLifecycleOwner,
            { resultWrapper: ResultWrapper<List<TeamDomain>> ->
                when (resultWrapper) {
                    is ResultWrapper.Loading -> {
                        //Todo mostrar loading

                    }
                    is ResultWrapper.Success -> {
                        val data = resultWrapper.data
                        teamsAdapter.submitList(data)
                    }
                    is ResultWrapper.Error -> {
                        //Todo mostrar error
                    }
                }
            })
    }

    private fun setupRecyclerView() {
        teamsAdapter = TeamsAdapter(this)
        listTeamBinding.recyclerViewTeams.run {
            adapter = teamsAdapter
            layoutManager =
                GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onClick(team: TeamDomain) {
        val action =
            ListTeamsDirections.actionListTeamsToDetailTeamFragment(team.idTeam, team.strTeam)
        findNavController().navigate(action)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                leagueName = data?.getStringExtra(DATA_NAME) ?: ""
                getTeamsLeague(leagueName)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.canceled_action),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getTeamsLeague(leagueName: String) {
        if (leagueName.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_league_selected),
                Toast.LENGTH_LONG
            ).show()
        } else {
            teamsListViewModel.getTeams(leagueName)
            listTeamBinding.textViewLeagueTitle.text = leagueName
        }
    }

    override fun onDetach() {
        teamsListViewModel.clearViewModel()
        super.onDetach()
    }

}