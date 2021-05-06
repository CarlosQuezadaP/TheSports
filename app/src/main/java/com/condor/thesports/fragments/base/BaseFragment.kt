package com.condor.thesports.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.condor.thesports.utils.ToastUtils
import com.condor.thesports.viewmodels.base.BaseViewModel
import org.koin.android.ext.android.inject

abstract class BaseFragment<out VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    open val viewModel: VM? = null

    open lateinit var databinding: DB

    abstract val layoutId: Int

    val toastUtils: ToastUtils by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        databinding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )

        return databinding.root

    }

    override fun onDetach() {
        viewModel?.clearViewModel()
        super.onDetach()
    }


}