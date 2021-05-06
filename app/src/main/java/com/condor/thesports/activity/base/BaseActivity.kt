package com.condor.thesports.activity.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.condor.thesports.utils.ToastUtils
import org.koin.android.ext.android.inject


abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    open var databinding: DB? = null

    val toastUtils: ToastUtils by inject()

    override fun onDestroy() {
        super.onDestroy()
        databinding = null
    }

}