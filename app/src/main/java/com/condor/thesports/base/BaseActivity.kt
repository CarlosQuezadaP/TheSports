package com.condor.thesports.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
    }

}