package com.condor.thesports.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.TeamsAdapter
import com.condor.thesports.databinding.FragmentListTeamsBinding
import com.condor.thesports.handlers.OnTeamClick
import com.condor.thesports.helpers.setExitToFullScreenTransition
import com.condor.thesports.helpers.setReturnFromFullScreenTransition
import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.android.ext.android.inject

class ListTeams : Fragment(), OnTeamClick {

    private lateinit var listTeamBinding: FragmentListTeamsBinding

    private val teamsListViewModel: TeamsListViewModel by inject()

    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        listTeamBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_teams, container, false
        )

        setupRecyclerView()

        teamsListViewModel.getTeams("Spanish La Liga")

        observeTeams()

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
        teamsAdapter = TeamsAdapter(this)
        listTeamBinding.recyclerViewTeams.run {
            adapter = teamsAdapter
            layoutManager =
                GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onClick(team: TeamDomain) {
        val action = ListTeamsDirections.actionListTeamsToDetailTeamFragment(team.idTeam)
        findNavController().navigate(action)
    }


}