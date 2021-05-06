package com.condor.thesports.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.condor.core.ResultWrapper
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.EventListAdapter
import com.condor.thesports.databinding.FragmentDetailTeamBinding
import com.condor.thesports.fragments.base.BaseFragment
import com.condor.thesports.handlers.OnClick
import com.condor.thesports.viewmodels.TeamDetailViewModel
import org.koin.android.ext.android.inject

class DetailTeamFragment : BaseFragment<TeamDetailViewModel, FragmentDetailTeamBinding>(),
    OnClick<EventDomain> {

    private val args: DetailTeamFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.fragment_detail_team

    override val viewModel: TeamDetailViewModel by inject()

    private lateinit var eventListAdapter: EventListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.teamId
        val name = args.nameTeam

        databinding.toolbar.title = name

        viewModel.apply {
            getTeam(id)
            getEventsByTeamId(id)
        }

        setupEventRecyclerView()
        executeObservers()
    }

    private fun executeObservers() {

        viewModel.apply {

            teamLiveData.observe(
                viewLifecycleOwner
            ) { teamWrapper: ResultWrapper<TeamDomain> ->

                when (teamWrapper) {
                    is ResultWrapper.Loading -> {
                        databinding.loading.visibility = View.VISIBLE
                    }
                    is ResultWrapper.Success -> {

                        databinding.apply {
                            loading.visibility = View.GONE
                            tvTeamFoundationYear.text =
                                teamWrapper.data.intFormedYear
                            tvDescriptionTeamDetail.text =
                                teamWrapper.data.strDescriptionEN
                        }

                        loadImage(
                            teamWrapper.data.strTeamBadge,
                            databinding.imageViewCoverPhoto
                        )
                        loadImage(
                            teamWrapper.data.strTeamJersey,
                            databinding.imageViewJersey
                        )
                    }

                    is ResultWrapper.Error -> {
                        databinding.loading.visibility = View.GONE
                        toastUtils.showLong(teamWrapper.message)
                    }
                }
            }

            eventsLiveData.observe(
                viewLifecycleOwner
            ) { eventsWrapper: ResultWrapper<List<EventDomain>> ->

                when (eventsWrapper) {
                    is ResultWrapper.Loading -> {
                        //TODO loading
                        var loading = ""

                    }
                    is ResultWrapper.Success -> {
                        eventListAdapter.submitList(eventsWrapper.data)
                    }

                    is ResultWrapper.Error -> {
                        toastUtils.showLong(eventsWrapper.message)
                    }
                }
            }

            loading.observe(viewLifecycleOwner) { isLoading: Boolean ->
                if (isLoading) databinding.loading.visibility =
                    View.VISIBLE else databinding.loading.visibility = View.GONE
            }

        }
    }

    private fun setupEventRecyclerView() {
        eventListAdapter = EventListAdapter(this)
        databinding.recyclerViewTeamEvents.adapter = eventListAdapter
    }
    private fun loadImage(strUrlImage: String, imageView: ImageView) {
        Glide.with(this).load(strUrlImage)
            .placeholder(R.drawable.ic_cloud_off_black_24dp).into(imageView)
    }

    override fun OnClick(data: EventDomain) {
    }


}