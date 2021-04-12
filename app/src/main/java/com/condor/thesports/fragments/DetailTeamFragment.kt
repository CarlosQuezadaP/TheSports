package com.condor.thesports.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.condor.thesports.R


class DetailTeamFragment : Fragment() {

    val args: DetailTeamFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val id = args.teamId
        return inflater.inflate(R.layout.fragment_detail_team, container, false)
    }


}