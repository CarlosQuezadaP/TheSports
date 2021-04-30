package com.condor.thesports.helpers

import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.condor.thesports.R

fun Fragment.setExitToFullScreenTransition() {
    exitTransition =
        TransitionInflater.from(context)
            .inflateTransition(R.transition.list_exit_transition)
}

fun Fragment.setReturnFromFullScreenTransition() {
    reenterTransition =
        TransitionInflater.from(context)
            .inflateTransition(R.transition.list_exit_transition)
}








