package com.condor.thesports.activity.base

import androidx.databinding.ViewDataBinding
import com.condor.thesports.viewmodels.base.BaseViewModel


abstract class BaseActivityVM<out VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity<DB>() {


    open val viewModel: VM? = null

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.clearViewModel()
        databinding = null
    }

}