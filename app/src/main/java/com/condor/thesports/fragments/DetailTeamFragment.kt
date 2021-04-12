package com.condor.thesports.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.condor.core.ResultWrapper
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.EventListAdapter
import com.condor.thesports.databinding.FragmentDetailTeamBinding
import com.condor.thesports.viewmodels.TeamDetailViewModel
import org.koin.android.ext.android.inject


class DetailTeamFragment : Fragment() {

    val args: DetailTeamFragmentArgs by navArgs()

    private lateinit var fragmentDetailBinding: FragmentDetailTeamBinding

    private val teamDetailViewModel: TeamDetailViewModel by inject()

    private lateinit var eventListAdapter: EventListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        fragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_team, container, false
        )
        val id = args.teamId
        val name = args.nameTeam

        fragmentDetailBinding.toolbar.title = name
        teamDetailViewModel.getTeam(id)
        teamDetailViewModel.getEventsByTeamId(id)


        setupEventRecyclerView()

        executeObservers()

        return fragmentDetailBinding.root
    }


    private fun executeObservers() {
        teamDetailViewModel.teamLiveData.observe(
            viewLifecycleOwner,
            { teamWrapper: ResultWrapper<TeamDomain> ->

                when (teamWrapper) {
                    is ResultWrapper.Loading -> {
                        fragmentDetailBinding.loading.visibility = View.VISIBLE
                    }

                    is ResultWrapper.Success -> {

                        fragmentDetailBinding.loading.visibility = View.GONE
                        fragmentDetailBinding.tvTeamFoundationYear.text =
                            teamWrapper.data.intFormedYear
                        fragmentDetailBinding.tvDescriptionTeamDetail.text =
                            teamWrapper.data.strDescriptionEN


                        loadImage(
                            teamWrapper.data.strTeamBadge,
                            fragmentDetailBinding.imageViewCoverPhoto
                        )
                        loadImage(
                            teamWrapper.data.strTeamJersey,
                            fragmentDetailBinding.imageViewJersey
                        )
                    }

                    is ResultWrapper.Error -> {
                        fragmentDetailBinding.loading.visibility = View.GONE
                        Toast.makeText(context, teamWrapper.message, Toast.LENGTH_LONG).show()
                    }
                }
            })

        teamDetailViewModel.eventsLiveData.observe(
            viewLifecycleOwner,
            { eventsWrapper: ResultWrapper<List<EventDomain>> ->

                when (eventsWrapper) {
                    is ResultWrapper.Loading -> {
                        var loading = ""

                    }
                    is ResultWrapper.Success -> {
                        eventListAdapter.submitList(eventsWrapper.data)
                    }

                    is ResultWrapper.Error -> {
                        Toast.makeText(context, eventsWrapper.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })

        teamDetailViewModel.loading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) fragmentDetailBinding.loading.visibility =
                View.VISIBLE else fragmentDetailBinding.loading.visibility = View.GONE
        })
    }

    private fun setupEventRecyclerView() {
        eventListAdapter = EventListAdapter()
        fragmentDetailBinding.recyclerViewTeamEvents.adapter = eventListAdapter
    }


    private fun loadImage(strUrlImage: String, imageView: ImageView) {
        Glide.with(this).load(strUrlImage)
            .placeholder(R.drawable.ic_cloud_off_black_24dp).into(imageView)
    }


}